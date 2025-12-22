package com.yms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yms.entity.Vehicle;
import com.yms.service.YardService;

@RestController
@RequestMapping("/api/yard")
@CrossOrigin
public class YardController {

    private final YardService yardService;

    public YardController(YardService yardService) {
        this.yardService = yardService;
    }

    @PostMapping("/allocate/{vehicleId}")
    public ResponseEntity<Vehicle> allocate(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(yardService.allocateToYard(vehicleId));
    }
}
