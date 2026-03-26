package com.waheedfruittrader.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waheedfruittrader.config.WhatsAppConfig;
import com.waheedfruittrader.exception.WhatsAppException;
import com.waheedfruittrader.mapper.TransactionMapper;
import com.waheedfruittrader.model.dto.WhatsAppMessageDTO;
import com.waheedfruittrader.model.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for WhatsApp notification integration.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WhatsAppService {

    private final WhatsAppConfig whatsAppConfig;
    private final TransactionMapper transactionMapper;
    private final ObjectMapper objectMapper;

    /**
     * Send order confirmation via WhatsApp.
     */
    public void sendOrderConfirmation(Long transactionId) {
        if (!whatsAppConfig.isEnabled()) {
            log.info("WhatsApp notifications are disabled");
            return;
        }

        try {
            Transaction transaction = transactionMapper.findById(transactionId);
            if (transaction == null) {
                log.warn("Transaction not found for WhatsApp notification: {}", transactionId);
                return;
            }

            String message = String.format(
                    "Dear Customer,\n\nYour order #%s has been confirmed.\nTotal Amount: PKR %.2f\n\nThank you for your business!\n\nWaheed Fruit Trader",
                    transaction.getTransactionNumber(),
                    transaction.getTotalAmount());

            sendMessage(transaction.getCustomerName(), message);
            log.info("Order confirmation sent for transaction: {}", transaction.getTransactionNumber());
        } catch (Exception e) {
            log.error("Failed to send WhatsApp order confirmation: {}", e.getMessage());
        }
    }

    /**
     * Send stock alert notification.
     */
    public void sendLowStockAlert(String fruitName, double quantity, String adminPhone) {
        if (!whatsAppConfig.isEnabled()) {
            return;
        }

        String message = String.format(
                "⚠️ LOW STOCK ALERT\n\nFruit: %s\nCurrent Stock: %.2f\n\nPlease reorder soon.",
                fruitName, quantity);

        sendMessage(adminPhone, message);
    }

    /**
     * Send a custom WhatsApp message.
     */
    public void sendMessage(WhatsAppMessageDTO dto) {
        if (!whatsAppConfig.isEnabled()) {
            log.info("WhatsApp disabled. Message to {}: {}", dto.getRecipientPhone(), dto.getContent());
            return;
        }
        sendMessage(dto.getRecipientPhone(), dto.getContent());
    }

    private void sendMessage(String phone, String message) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("messaging_product", "whatsapp");
            payload.put("to", phone);
            payload.put("type", "text");
            Map<String, String> text = new HashMap<>();
            text.put("body", message);
            payload.put("text", text);

            String jsonBody = objectMapper.writeValueAsString(payload);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(whatsAppConfig.getApiUrl() + "/" +
                            whatsAppConfig.getPhoneNumberId() + "/messages"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + whatsAppConfig.getApiToken())
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200 && response.statusCode() != 201) {
                throw new WhatsAppException("Failed to send message. Status: " + response.statusCode());
            }
            log.info("WhatsApp message sent to {}", phone);
        } catch (WhatsAppException e) {
            throw e;
        } catch (Exception e) {
            throw new WhatsAppException("Error sending WhatsApp message: " + e.getMessage(), e);
        }
    }
}
