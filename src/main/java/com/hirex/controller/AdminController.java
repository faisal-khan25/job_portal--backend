package com.hirex.controller;

import com.hirex.service.AdminService;
import com.hirex.service.AdminService.DashboardStats;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardStats> dashboard() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }
}
