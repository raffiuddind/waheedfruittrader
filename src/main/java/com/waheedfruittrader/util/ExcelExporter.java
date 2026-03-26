package com.waheedfruittrader.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Utility class for exporting data to Excel format.
 */
@Slf4j
@Component
public class ExcelExporter {

    /**
     * Export sales report to Excel.
     */
    public byte[] exportSalesReport(List<Map<String, Object>> salesData,
                                     LocalDate startDate, LocalDate endDate) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Sales Report");

            // Header row
            CellStyle headerStyle = createHeaderStyle(workbook);
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Waheed Fruit Trader - Sales Report");

            Row dateRow = sheet.createRow(1);
            dateRow.createCell(0).setCellValue(
                    "Period: " + startDate + " to " + endDate);

            Row headerRow = sheet.createRow(3);
            String[] headers = {"Fruit Name", "Unit", "Qty Sold", "Revenue (PKR)"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 4;
            for (Map<String, Object> rowData : salesData) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(String.valueOf(rowData.get("fruit_name")));
                row.createCell(1).setCellValue(String.valueOf(rowData.get("unit")));
                row.createCell(2).setCellValue(String.valueOf(rowData.get("total_quantity")));
                row.createCell(3).setCellValue(String.valueOf(rowData.get("total_revenue")));
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("Error generating sales Excel: {}", e.getMessage());
            throw new RuntimeException("Failed to generate Excel report", e);
        }
    }

    /**
     * Export inventory report to Excel.
     */
    public byte[] exportInventoryReport(List<Map<String, Object>> inventoryData) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Inventory Report");
            CellStyle headerStyle = createHeaderStyle(workbook);

            Row headerRow = sheet.createRow(0);
            String[] headers = {"Fruit Name", "Unit", "Quantity", "Status"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (Map<String, Object> rowData : inventoryData) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(String.valueOf(rowData.get("fruit_name")));
                row.createCell(1).setCellValue(String.valueOf(rowData.get("unit")));
                row.createCell(2).setCellValue(String.valueOf(rowData.get("total_quantity")));
                row.createCell(3).setCellValue(String.valueOf(rowData.get("stock_status")));
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("Error generating inventory Excel: {}", e.getMessage());
            throw new RuntimeException("Failed to generate Excel report", e);
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }
}
