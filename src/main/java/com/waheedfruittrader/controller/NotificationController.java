package com.waheedfruittrader.controller;

import com.waheedfruittrader.model.dto.ApiResponse;
import com.waheedfruittrader.model.dto.WhatsAppMessageDTO;
import com.waheedfruittrader.service.WhatsAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for Notifications and WhatsApp messaging.
 */
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "Notification and WhatsApp messaging")
public class NotificationController {

    private final WhatsAppService whatsAppService;

    @PostMapping("/whatsapp/send")
    @Operation(summary = "Send a WhatsApp message")
    public ResponseEntity<ApiResponse<Void>> sendWhatsApp(
            @Valid @RequestBody WhatsAppMessageDTO dto) {
        whatsAppService.sendMessage(dto);
        return ResponseEntity.ok(ApiResponse.success("Message sent", null));
    }

    @PostMapping("/whatsapp/order-confirmation/{transactionId}")
    @Operation(summary = "Send order confirmation via WhatsApp")
    public ResponseEntity<ApiResponse<Void>> sendOrderConfirmation(
            @PathVariable Long transactionId) {
        whatsAppService.sendOrderConfirmation(transactionId);
        return ResponseEntity.ok(ApiResponse.success("Order confirmation sent", null));
    }
}
