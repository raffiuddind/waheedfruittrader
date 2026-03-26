package com.waheedfruittrader.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing an inventory storage location.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryLocation {

    private Long id;
    private String name;
    private String address;
    private String description;
    private Boolean active;
    private LocalDateTime createdAt;
}
