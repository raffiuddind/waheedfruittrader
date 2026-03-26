package com.waheedfruittrader.controller;

import com.waheedfruittrader.model.dto.ApiResponse;
import com.waheedfruittrader.model.dto.DashboardMetricsDTO;
import com.waheedfruittrader.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for Dashboard metrics.
 */
@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Dashboard metrics and analytics")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    @Operation(summary = "Get dashboard metrics")
    public ResponseEntity<ApiResponse<DashboardMetricsDTO>> getDashboardMetrics() {
        return ResponseEntity.ok(ApiResponse.success(dashboardService.getDashboardMetrics()));
    }
}
