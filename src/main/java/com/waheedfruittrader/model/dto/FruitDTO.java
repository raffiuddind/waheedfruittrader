package com.waheedfruittrader.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for Fruit data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FruitDTO {

    private Long id;

    @NotBlank(message = "Fruit name is required")
    private String name;

    private String type;
    private String category;

    @NotBlank(message = "Unit is required")
    private String unit;

    @NotNull(message = "Purchase price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Purchase price must be positive")
    private BigDecimal purchasePrice;

    @NotNull(message = "Selling price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Selling price must be positive")
    private BigDecimal sellingPrice;

    private String description;
    private String imageUrl;
    private Boolean active;
    private LocalDateTime createdAt;
}
