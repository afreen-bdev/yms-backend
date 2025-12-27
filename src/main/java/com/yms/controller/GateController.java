package com.yms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.yms.dto.VehicleRequest;
import com.yms.entity.Vehicle;
import com.yms.service.VehicleService;

@RestController
@RequestMapping("/api/gate")
public class GateController {

    private final VehicleService vehicleService;

    public GateController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('GATE')")
    public ResponseEntity<String> registerVehicle(@RequestBody VehicleRequest request) {
    	vehicleService.registerVehicle(request);
        return ResponseEntity.ok("Vehicle registered successfully");
    }
}