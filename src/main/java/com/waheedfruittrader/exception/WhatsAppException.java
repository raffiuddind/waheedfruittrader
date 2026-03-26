package com.waheedfruittrader.exception;

/**
 * Exception thrown when WhatsApp API operations fail.
 */
public class WhatsAppException extends RuntimeException {

    public WhatsAppException(String message) {
        super(message);
    }

    public WhatsAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
