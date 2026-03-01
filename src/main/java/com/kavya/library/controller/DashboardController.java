package com.kavya.library.controller;

import com.kavya.library.dto.DashboardResponseDTO;
import com.kavya.library.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboardStats() {

        DashboardResponseDTO response = dashboardService.getDashboardStats();

        return ResponseEntity.ok(response);
    }
}
