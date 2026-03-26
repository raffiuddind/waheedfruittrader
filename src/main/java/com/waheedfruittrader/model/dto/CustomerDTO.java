package com.waheedfruittrader.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for Customer data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;

    @NotBlank(message = "Customer name is required")
    private String name;

    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    private String address;
    private String city;
    private String notes;
    private BigDecimal creditLimit;
    private BigDecimal outstandingBalance;
    private Boolean active;
    private LocalDateTime createdAt;
}
