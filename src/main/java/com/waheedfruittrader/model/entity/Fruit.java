package com.waheedfruittrader.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a fruit product in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fruit {

    private Long id;
    private String name;
    private String type;
    private String category;
    private String unit;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private String description;
    private String imageUrl;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
