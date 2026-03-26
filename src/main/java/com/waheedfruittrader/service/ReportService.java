package com.waheedfruittrader.service;

import com.waheedfruittrader.mapper.ReportMapper;
import com.waheedfruittrader.model.dto.ReportDTO;
import com.waheedfruittrader.util.ExcelExporter;
import com.waheedfruittrader.util.PdfGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 * Service for report generation.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportMapper reportMapper;
    private final PdfGenerator pdfGenerator;
    private final ExcelExporter excelExporter;

    public ReportDTO getSalesReport(LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);

        BigDecimal totalSales = reportMapper.getTotalSalesByDateRange(start, end);
        BigDecimal totalPurchases = reportMapper.getTotalPurchasesByDateRange(start, end);

        return ReportDTO.builder()
                .reportType("SALES")
                .startDate(startDate)
                .endDate(endDate)
                .totalSales(totalSales)
                .totalPurchases(totalPurchases)
                .totalProfit(totalSales.subtract(totalPurchases))
                .build();
    }

    public byte[] generateSalesPdf(LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);
        List<Map<String, Object>> salesData = reportMapper.getSalesByFruit(start, end);
        return pdfGenerator.generateSalesReport(salesData, startDate, endDate);
    }

    public byte[] generateSalesExcel(LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);
        List<Map<String, Object>> salesData = reportMapper.getSalesByFruit(start, end);
        return excelExporter.exportSalesReport(salesData, startDate, endDate);
    }

    public byte[] generateInventoryPdf() {
        List<Map<String, Object>> inventoryData = reportMapper.getInventorySummary();
        return pdfGenerator.generateInventoryReport(inventoryData);
    }

    public byte[] generateInventoryExcel() {
        List<Map<String, Object>> inventoryData = reportMapper.getInventorySummary();
        return excelExporter.exportInventoryReport(inventoryData);
    }
}
