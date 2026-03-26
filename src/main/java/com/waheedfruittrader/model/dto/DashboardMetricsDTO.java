package com.waheedfruittrader.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * DTO for dashboard metrics.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardMetricsDTO {

    private BigDecimal todaySales;
    private BigDecimal todayPurchases;
    private BigDecimal monthSales;
    private BigDecimal monthPurchases;
    private BigDecimal totalRevenue;
    private Long totalCustomers;
    private Long totalSuppliers;
    private Long totalFruits;
    private Long lowStockAlerts;
    private Long pendingTransactions;
    private List<Map<String, Object>> recentTransactions;
    private List<Map<String, Object>> topFruits;
    private List<Map<String, Object>> salesTrend;
}
