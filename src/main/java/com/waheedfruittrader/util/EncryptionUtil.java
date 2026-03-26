package com.waheedfruittrader.util;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * Utility class for encryption and hashing operations.
 */
public final class EncryptionUtil {

    private EncryptionUtil() {}

    /**
     * Generate SHA-256 hash of a string.
     */
    public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error computing hash", e);
        }
    }

    /**
     * Encode string to Base64.
     */
    public static String base64Encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    /**
     * Decode Base64 string.
     */
    public static String base64Decode(String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }
}
