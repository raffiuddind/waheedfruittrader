package com.waheedfruittrader.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for WhatsApp message data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhatsAppMessageDTO {

    private Long id;

    @NotBlank(message = "Recipient phone is required")
    private String recipientPhone;

    private String recipientName;
    private String messageType;
    private String content;
    private String status;
    private String errorMessage;
    private Long relatedEntityId;
    private String relatedEntityType;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
}
