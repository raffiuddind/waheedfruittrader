package com.waheedfruittrader.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Utility class for generating PDF reports.
 */
@Slf4j
@Component
public class PdfGenerator {

    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

    /**
     * Generate sales report PDF.
     */
    public byte[] generateSalesReport(List<Map<String, Object>> salesData,
                                       LocalDate startDate, LocalDate endDate) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, baos);
            document.open();

            // Title
            Paragraph title = new Paragraph("Waheed Fruit Trader - Sales Report", TITLE_FONT);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Date range
            String dateRange = String.format("Period: %s to %s",
                    startDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                    endDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
            document.add(new Paragraph(dateRange, NORMAL_FONT));
            document.add(Chunk.NEWLINE);

            // Table
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            addTableHeader(table, "Fruit Name", "Unit", "Qty Sold", "Revenue (PKR)");

            BigDecimal totalRevenue = BigDecimal.ZERO;
            for (Map<String, Object> row : salesData) {
                addTableCell(table, String.valueOf(row.get("fruit_name")));
                addTableCell(table, String.valueOf(row.get("unit")));
                addTableCell(table, String.valueOf(row.get("total_quantity")));
                Object revenue = row.get("total_revenue");
                addTableCell(table, revenue != null ? revenue.toString() : "0");
                if (revenue instanceof BigDecimal) {
                    totalRevenue = totalRevenue.add((BigDecimal) revenue);
                }
            }

            document.add(table);
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Total Revenue: PKR " + totalRevenue, HEADER_FONT));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("Error generating sales PDF: {}", e.getMessage());
            throw new RuntimeException("Failed to generate PDF report", e);
        }
    }

    /**
     * Generate inventory report PDF.
     */
    public byte[] generateInventoryReport(List<Map<String, Object>> inventoryData) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, baos);
            document.open();

            Paragraph title = new Paragraph("Waheed Fruit Trader - Inventory Report", TITLE_FONT);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            addTableHeader(table, "Fruit Name", "Unit", "Quantity", "Status");

            for (Map<String, Object> row : inventoryData) {
                addTableCell(table, String.valueOf(row.get("fruit_name")));
                addTableCell(table, String.valueOf(row.get("unit")));
                addTableCell(table, String.valueOf(row.get("total_quantity")));
                addTableCell(table, String.valueOf(row.get("stock_status")));
            }

            document.add(table);
            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("Error generating inventory PDF: {}", e.getMessage());
            throw new RuntimeException("Failed to generate PDF report", e);
        }
    }

    private void addTableHeader(PdfPTable table, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, HEADER_FONT));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPadding(5);
            table.addCell(cell);
        }
    }

    private void addTableCell(PdfPTable table, String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, NORMAL_FONT));
        cell.setPadding(4);
        table.addCell(cell);
    }
}
