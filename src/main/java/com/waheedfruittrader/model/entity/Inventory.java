package com.waheedfruittrader.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing inventory stock.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    private Long id;
    private Long fruitId;
    private Long locationId;
    private BigDecimal quantity;
    private BigDecimal lowStockThreshold;
    private LocalDateTime lastUpdated;

    // Transient fields
    private String fruitName;
    private String locationName;
}
