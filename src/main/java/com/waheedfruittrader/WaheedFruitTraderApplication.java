package com.waheedfruittrader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main entry point for the Waheed Fruit Trader Spring Boot application.
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
public class WaheedFruitTraderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaheedFruitTraderApplication.class, args);
        log.info("✓ Waheed Fruit Trader Application Started");
    }
}