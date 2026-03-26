package com.waheedfruittrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main entry point for the Waheed Fruit Trader Spring Boot application.
 */
@SpringBootApplication
@EnableScheduling
public class WaheedFruitTraderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaheedFruitTraderApplication.class, args);
        System.out.println("✓ Waheed Fruit Trader Application Started");
    }
}