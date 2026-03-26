package com.waheedfruittrader.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for Inventory data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

    private Long id;
    private Long fruitId;
    private String fruitName;
    private Long locationId;
    private String locationName;
    private BigDecimal quantity;
    private BigDecimal lowStockThreshold;
    private boolean lowStock;
    private LocalDateTime lastUpdated;
}
