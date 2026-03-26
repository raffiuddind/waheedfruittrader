package com.waheedfruittrader.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for Transaction data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private String transactionNumber;

    @NotNull(message = "Transaction type is required")
    private String type;

    private String status;
    private Long customerId;
    private String customerName;
    private Long supplierId;
    private String supplierName;
    private BigDecimal subtotal;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;
    private String paymentMethod;
    private String notes;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;

    @NotEmpty(message = "Transaction must have at least one item")
    private List<TransactionItemDTO> items;
}
