package com.waheedfruittrader.controller;

import com.waheedfruittrader.model.dto.ApiResponse;
import com.waheedfruittrader.model.dto.InventoryDTO;
import com.waheedfruittrader.model.entity.InventoryLocation;
import com.waheedfruittrader.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for Inventory management.
 */
@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@Tag(name = "Inventory", description = "Inventory management operations")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @Operation(summary = "Get all inventory")
    public ResponseEntity<ApiResponse<List<InventoryDTO>>> getAllInventory() {
        return ResponseEntity.ok(ApiResponse.success(inventoryService.getAllInventory()));
    }

    @GetMapping("/low-stock")
    @Operation(summary = "Get low stock alerts")
    public ResponseEntity<ApiResponse<List<InventoryDTO>>> getLowStock() {
        return ResponseEntity.ok(ApiResponse.success(inventoryService.getLowStockAlerts()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get inventory by ID")
    public ResponseEntity<ApiResponse<InventoryDTO>> getInventoryById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(inventoryService.getInventoryById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update inventory")
    public ResponseEntity<ApiResponse<InventoryDTO>> updateInventory(
            @PathVariable Long id, @RequestBody InventoryDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Inventory updated",
                inventoryService.updateInventory(id, dto)));
    }

    @PostMapping("/adjust")
    @Operation(summary = "Adjust stock quantity")
    public ResponseEntity<ApiResponse<Void>> adjustStock(
            @RequestParam Long fruitId,
            @RequestParam Long locationId,
            @RequestParam BigDecimal quantity) {
        inventoryService.adjustStock(fruitId, locationId, quantity);
        return ResponseEntity.ok(ApiResponse.success("Stock adjusted", null));
    }

    @GetMapping("/locations")
    @Operation(summary = "Get all inventory locations")
    public ResponseEntity<ApiResponse<List<InventoryLocation>>> getLocations() {
        return ResponseEntity.ok(ApiResponse.success(inventoryService.getAllLocations()));
    }

    @PostMapping("/locations")
    @Operation(summary = "Create an inventory location")
    public ResponseEntity<ApiResponse<InventoryLocation>> createLocation(
            @RequestBody InventoryLocation location) {
        return ResponseEntity.ok(ApiResponse.success("Location created",
                inventoryService.createLocation(location)));
    }
}
