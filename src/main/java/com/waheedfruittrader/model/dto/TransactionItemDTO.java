package com.waheedfruittrader.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for TransactionItem data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionItemDTO {

    private Long id;
    private Long transactionId;

    @NotNull(message = "Fruit ID is required")
    private Long fruitId;

    private String fruitName;
    private String unit;
    private Long locationId;

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be positive")
    private BigDecimal quantity;

    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be positive")
    private BigDecimal unitPrice;

    private BigDecimal totalPrice;
    private BigDecimal discountPercent;
}
