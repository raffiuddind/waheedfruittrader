package com.waheedfruittrader.service;

import com.waheedfruittrader.exception.BusinessException;
import com.waheedfruittrader.exception.ResourceNotFoundException;
import com.waheedfruittrader.mapper.InventoryMapper;
import com.waheedfruittrader.mapper.TransactionMapper;
import com.waheedfruittrader.model.dto.TransactionDTO;
import com.waheedfruittrader.model.dto.TransactionItemDTO;
import com.waheedfruittrader.model.entity.Transaction;
import com.waheedfruittrader.model.entity.TransactionItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Transaction processing.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;
    private final InventoryMapper inventoryMapper;

    public List<TransactionDTO> getAllTransactions() {
        return transactionMapper.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> searchTransactions(String type, String status,
                                                    Long customerId, Long supplierId,
                                                    LocalDateTime startDate, LocalDateTime endDate) {
        return transactionMapper.findBySearch(type, status, customerId, supplierId, startDate, endDate)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TransactionDTO getTransactionById(Long id) {
        Transaction t = transactionMapper.findById(id);
        if (t == null) throw new ResourceNotFoundException("Transaction", id);
        t.setItems(transactionMapper.findItemsByTransactionId(id));
        return toDTO(t);
    }

    /**
     * Create a new transaction and update inventory.
     */
    @Transactional
    public TransactionDTO createTransaction(TransactionDTO dto, Long userId) {
        // Generate transaction number
        String txNumber = transactionMapper.generateTransactionNumber(dto.getType());
        if (txNumber == null) {
            txNumber = dto.getType() + "-" + System.currentTimeMillis();
        }

        // Calculate totals
        BigDecimal subtotal = dto.getItems().stream()
                .map(item -> item.getUnitPrice().multiply(item.getQuantity()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = dto.getDiscountAmount() != null ? dto.getDiscountAmount() : BigDecimal.ZERO;
        BigDecimal tax = dto.getTaxAmount() != null ? dto.getTaxAmount() : BigDecimal.ZERO;
        BigDecimal total = subtotal.subtract(discount).add(tax);
        BigDecimal paid = dto.getPaidAmount() != null ? dto.getPaidAmount() : BigDecimal.ZERO;

        Transaction transaction = Transaction.builder()
                .transactionNumber(txNumber)
                .type(dto.getType())
                .status("PENDING")
                .customerId(dto.getCustomerId())
                .supplierId(dto.getSupplierId())
                .subtotal(subtotal)
                .discountAmount(discount)
                .taxAmount(tax)
                .totalAmount(total)
                .paidAmount(paid)
                .dueAmount(total.subtract(paid))
                .paymentMethod(dto.getPaymentMethod())
                .notes(dto.getNotes())
                .transactionDate(dto.getTransactionDate() != null ? dto.getTransactionDate() : LocalDateTime.now())
                .createdBy(userId)
                .build();

        transactionMapper.insert(transaction);

        // Insert items and adjust inventory
        for (TransactionItemDTO itemDTO : dto.getItems()) {
            BigDecimal itemTotal = itemDTO.getUnitPrice().multiply(itemDTO.getQuantity());
            TransactionItem item = TransactionItem.builder()
                    .transactionId(transaction.getId())
                    .fruitId(itemDTO.getFruitId())
                    .unit(itemDTO.getUnit())
                    .quantity(itemDTO.getQuantity())
                    .unitPrice(itemDTO.getUnitPrice())
                    .totalPrice(itemTotal)
                    .discountPercent(itemDTO.getDiscountPercent())
                    .build();
            transactionMapper.insertItem(item);

            // Adjust inventory: sales reduce stock, purchases increase stock
            BigDecimal qtyAdjustment = "SALE".equals(dto.getType())
                    ? itemDTO.getQuantity().negate()
                    : itemDTO.getQuantity();
            Long locationId = itemDTO.getLocationId() != null ? itemDTO.getLocationId() : 1L;
            inventoryMapper.adjustQuantity(itemDTO.getFruitId(), locationId, qtyAdjustment);
        }

        log.info("Created transaction: {}", txNumber);
        return getTransactionById(transaction.getId());
    }

    /**
     * Update transaction status.
     */
    @Transactional
    public TransactionDTO updateTransactionStatus(Long id, String status) {
        Transaction transaction = transactionMapper.findById(id);
        if (transaction == null) throw new ResourceNotFoundException("Transaction", id);

        validateStatusTransition(transaction.getStatus(), status);
        transaction.setStatus(status);
        transactionMapper.update(transaction);
        return getTransactionById(id);
    }

    @Transactional
    public void deleteTransaction(Long id) {
        Transaction t = transactionMapper.findById(id);
        if (t == null) throw new ResourceNotFoundException("Transaction", id);
        if (!"PENDING".equals(t.getStatus())) {
            throw new BusinessException("Only pending transactions can be deleted");
        }
        transactionMapper.deleteItemsByTransactionId(id);
        transactionMapper.deleteById(id);
    }

    private void validateStatusTransition(String from, String to) {
        if ("CANCELLED".equals(from) || "COMPLETED".equals(from)) {
            throw new BusinessException("Cannot change status of " + from + " transaction");
        }
    }

    private TransactionDTO toDTO(Transaction t) {
        TransactionDTO dto = TransactionDTO.builder()
                .id(t.getId())
                .transactionNumber(t.getTransactionNumber())
                .type(t.getType())
                .status(t.getStatus())
                .customerId(t.getCustomerId())
                .customerName(t.getCustomerName())
                .supplierId(t.getSupplierId())
                .supplierName(t.getSupplierName())
                .subtotal(t.getSubtotal())
                .discountAmount(t.getDiscountAmount())
                .taxAmount(t.getTaxAmount())
                .totalAmount(t.getTotalAmount())
                .paidAmount(t.getPaidAmount())
                .dueAmount(t.getDueAmount())
                .paymentMethod(t.getPaymentMethod())
                .notes(t.getNotes())
                .transactionDate(t.getTransactionDate())
                .createdAt(t.getCreatedAt())
                .build();

        if (t.getItems() != null) {
            dto.setItems(t.getItems().stream().map(this::toItemDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    private TransactionItemDTO toItemDTO(TransactionItem item) {
        return TransactionItemDTO.builder()
                .id(item.getId())
                .transactionId(item.getTransactionId())
                .fruitId(item.getFruitId())
                .fruitName(item.getFruitName())
                .unit(item.getUnit())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .totalPrice(item.getTotalPrice())
                .discountPercent(item.getDiscountPercent())
                .build();
    }
}
