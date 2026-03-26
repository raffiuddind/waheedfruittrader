package com.waheedfruittrader.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * MyBatis mapper interface for Report queries.
 */
@Mapper
public interface ReportMapper {

    BigDecimal getTotalSalesByDateRange(@Param("startDate") LocalDateTime startDate,
                                        @Param("endDate") LocalDateTime endDate);

    BigDecimal getTotalPurchasesByDateRange(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);

    List<Map<String, Object>> getSalesByFruit(@Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);

    List<Map<String, Object>> getTopCustomers(@Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate,
                                               @Param("limit") int limit);

    List<Map<String, Object>> getTopSuppliers(@Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate,
                                               @Param("limit") int limit);

    List<Map<String, Object>> getDailySalesTrend(@Param("startDate") LocalDateTime startDate,
                                                   @Param("endDate") LocalDateTime endDate);

    List<Map<String, Object>> getInventorySummary();

    Map<String, Object> getDashboardMetrics();
}
