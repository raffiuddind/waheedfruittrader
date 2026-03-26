package com.waheedfruittrader.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a customer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String notes;
    private BigDecimal creditLimit;
    private BigDecimal outstandingBalance;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
