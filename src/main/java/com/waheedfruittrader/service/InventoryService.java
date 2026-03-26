package com.waheedfruittrader.service;

import com.waheedfruittrader.exception.ResourceNotFoundException;
import com.waheedfruittrader.mapper.InventoryMapper;
import com.waheedfruittrader.model.dto.InventoryDTO;
import com.waheedfruittrader.model.entity.Inventory;
import com.waheedfruittrader.model.entity.InventoryLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Inventory management.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryMapper inventoryMapper;

    public List<InventoryDTO> getAllInventory() {
        return inventoryMapper.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<InventoryDTO> getLowStockAlerts() {
        return inventoryMapper.findLowStock().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public InventoryDTO getInventoryById(Long id) {
        Inventory inv = inventoryMapper.findById(id);
        if (inv == null) throw new ResourceNotFoundException("Inventory", id);
        return toDTO(inv);
    }

    @Transactional
    public InventoryDTO updateInventory(Long id, InventoryDTO dto) {
        Inventory existing = inventoryMapper.findById(id);
        if (existing == null) throw new ResourceNotFoundException("Inventory", id);
        existing.setQuantity(dto.getQuantity());
        existing.setLowStockThreshold(dto.getLowStockThreshold());
        inventoryMapper.update(existing);
        return getInventoryById(id);
    }

    @Transactional
    public void adjustStock(Long fruitId, Long locationId, BigDecimal quantity) {
        inventoryMapper.adjustQuantity(fruitId, locationId, quantity);
        log.info("Adjusted stock for fruit {} at location {} by {}", fruitId, locationId, quantity);
    }

    public List<InventoryLocation> getAllLocations() {
        return inventoryMapper.findAllLocations();
    }

    @Transactional
    public InventoryLocation createLocation(InventoryLocation location) {
        location.setActive(true);
        inventoryMapper.insertLocation(location);
        return location;
    }

    private InventoryDTO toDTO(Inventory inv) {
        boolean lowStock = inv.getLowStockThreshold() != null
                && inv.getQuantity().compareTo(inv.getLowStockThreshold()) <= 0;
        return InventoryDTO.builder()
                .id(inv.getId()).fruitId(inv.getFruitId()).fruitName(inv.getFruitName())
                .locationId(inv.getLocationId()).locationName(inv.getLocationName())
                .quantity(inv.getQuantity()).lowStockThreshold(inv.getLowStockThreshold())
                .lowStock(lowStock).lastUpdated(inv.getLastUpdated())
                .build();
    }
}
