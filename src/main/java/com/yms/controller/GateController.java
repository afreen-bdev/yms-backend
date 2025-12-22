package com.yms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yms.dto.VehicleRequest;
import com.yms.entity.Vehicle;
import com.yms.service.VehicleService;

@RestController
@RequestMapping("/api/gate")
@CrossOrigin
public class GateController {

    private final VehicleService vehicleService;

    public GateController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/register")
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.registerVehicle(request));
    }
}