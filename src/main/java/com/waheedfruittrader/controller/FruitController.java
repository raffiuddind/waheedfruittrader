package com.waheedfruittrader.controller;

import com.waheedfruittrader.model.dto.ApiResponse;
import com.waheedfruittrader.model.dto.FruitDTO;
import com.waheedfruittrader.service.FruitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Fruit management.
 */
@RestController
@RequestMapping("/api/v1/fruits")
@RequiredArgsConstructor
@Tag(name = "Fruits", description = "Fruit CRUD operations")
public class FruitController {

    private final FruitService fruitService;

    @GetMapping
    @Operation(summary = "Get all fruits")
    public ResponseEntity<ApiResponse<List<FruitDTO>>> getAllFruits(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean active) {
        List<FruitDTO> fruits = (keyword != null || type != null || category != null || active != null)
                ? fruitService.searchFruits(keyword, type, category, active)
                : fruitService.getAllFruits();
        return ResponseEntity.ok(ApiResponse.success(fruits));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get fruit by ID")
    public ResponseEntity<ApiResponse<FruitDTO>> getFruitById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(fruitService.getFruitById(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new fruit")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<FruitDTO>> createFruit(@Valid @RequestBody FruitDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Fruit created", fruitService.createFruit(dto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a fruit")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<FruitDTO>> updateFruit(
            @PathVariable Long id, @Valid @RequestBody FruitDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Fruit updated", fruitService.updateFruit(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a fruit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteFruit(@PathVariable Long id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.ok(ApiResponse.success("Fruit deleted", null));
    }
}
