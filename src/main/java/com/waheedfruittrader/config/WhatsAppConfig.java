package com.waheedfruittrader.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * WhatsApp API configuration properties.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "whatsapp")
public class WhatsAppConfig {

    private String apiUrl;
    private String apiToken;
    private String phoneNumberId;
    private String webhookVerifyToken;
    private boolean enabled = false;
}
