package com.waheedfruittrader.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis and database configuration.
 */
@Configuration
@MapperScan("com.waheedfruittrader.mapper")
@EnableTransactionManagement
public class DatabaseConfig {
}
