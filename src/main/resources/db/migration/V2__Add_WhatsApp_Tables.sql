-- V2: Add WhatsApp message tracking tables

CREATE TABLE whatsapp_messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recipient_phone VARCHAR(20) NOT NULL,
    recipient_name VARCHAR(100),
    message_type VARCHAR(50),
    content TEXT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    error_message TEXT,
    related_entity_id BIGINT,
    related_entity_type VARCHAR(50),
    sent_at DATETIME,
    delivered_at DATETIME,
    created_at DATETIME NOT NULL
);

CREATE INDEX idx_whatsapp_status ON whatsapp_messages(status);
CREATE INDEX idx_whatsapp_phone ON whatsapp_messages(recipient_phone);
CREATE INDEX idx_whatsapp_created ON whatsapp_messages(created_at);
