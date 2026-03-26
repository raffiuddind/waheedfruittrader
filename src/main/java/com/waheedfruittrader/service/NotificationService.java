package com.waheedfruittrader.service;

import com.waheedfruittrader.mapper.InventoryMapper;
import com.waheedfruittrader.model.entity.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for scheduled notifications and alerts.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final InventoryMapper inventoryMapper;
    private final WhatsAppService whatsAppService;

    /**
     * Check for low stock items every hour.
     */
    @Scheduled(fixedDelay = 3600000)
    public void checkLowStock() {
        List<Inventory> lowStockItems = inventoryMapper.findLowStock();
        if (!lowStockItems.isEmpty()) {
            log.warn("Low stock alerts: {} items below threshold", lowStockItems.size());
            lowStockItems.forEach(inv ->
                    log.warn("Low stock: {} at location {} - quantity: {}",
                            inv.getFruitName(), inv.getLocationName(), inv.getQuantity()));
        }
    }
}
