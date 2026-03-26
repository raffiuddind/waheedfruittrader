package com.waheedfruittrader.service;

import com.waheedfruittrader.mapper.ReportMapper;
import com.waheedfruittrader.model.dto.DashboardMetricsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;

/**
 * Service for dashboard metrics and analytics.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ReportMapper reportMapper;

    /**
     * Get comprehensive dashboard metrics.
     */
    public DashboardMetricsDTO getDashboardMetrics() {
        Map<String, Object> metrics = reportMapper.getDashboardMetrics();

        LocalDateTime monthStart = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withMinute(0).withSecond(0);
        LocalDateTime now = LocalDateTime.now();

        BigDecimal monthSales = reportMapper.getTotalSalesByDateRange(monthStart, now);
        BigDecimal monthPurchases = reportMapper.getTotalPurchasesByDateRange(monthStart, now);

        List<Map<String, Object>> salesTrend = reportMapper.getDailySalesTrend(
                now.minusDays(30), now);

        List<Map<String, Object>> topFruits = reportMapper.getSalesByFruit(monthStart, now);

        return DashboardMetricsDTO.builder()
                .todaySales(getDecimal(metrics, "today_sales"))
                .todayPurchases(getDecimal(metrics, "today_purchases"))
                .monthSales(monthSales)
                .monthPurchases(monthPurchases)
                .totalCustomers(getLong(metrics, "total_customers"))
                .totalSuppliers(getLong(metrics, "total_suppliers"))
                .totalFruits(getLong(metrics, "total_fruits"))
                .lowStockAlerts(getLong(metrics, "low_stock_alerts"))
                .pendingTransactions(getLong(metrics, "pending_transactions"))
                .salesTrend(salesTrend)
                .topFruits(topFruits.size() > 5 ? topFruits.subList(0, 5) : topFruits)
                .build();
    }

    private BigDecimal getDecimal(Map<String, Object> map, String key) {
        Object val = map.get(key);
        if (val == null) return BigDecimal.ZERO;
        if (val instanceof BigDecimal) return (BigDecimal) val;
        return new BigDecimal(val.toString());
    }

    private Long getLong(Map<String, Object> map, String key) {
        Object val = map.get(key);
        if (val == null) return 0L;
        if (val instanceof Long) return (Long) val;
        return Long.parseLong(val.toString());
    }
}
