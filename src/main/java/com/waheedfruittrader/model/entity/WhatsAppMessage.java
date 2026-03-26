package com.waheedfruittrader.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity for tracking WhatsApp messages sent.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhatsAppMessage {

    private Long id;
    private String recipientPhone;
    private String recipientName;
    private String messageType;
    private String content;
    private String status; // PENDING, SENT, DELIVERED, FAILED
    private String errorMessage;
    private Long relatedEntityId;
    private String relatedEntityType;
    private LocalDateTime sentAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime createdAt;
}
