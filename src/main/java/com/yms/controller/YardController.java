package com.yms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yms.entity.Vehicle;
import com.yms.service.VehicleService;
import com.yms.service.YardService;

@RestController
@RequestMapping("/api/yard")
public class YardController {

    private final YardService yardService;
    private final VehicleService vehicleService;

    public YardController(YardService yardService, VehicleService vehicleService) {
        this.yardService = yardService;
        this.vehicleService = vehicleService;
    }

    @PostMapping("/allocate/{vehicleId}")
    public ResponseEntity<String> allocate(@PathVariable Long vehicleId) {
        yardService.allocateVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle allocated to yard");
    }

    @PostMapping("/exit/{vehicleId}")
    public ResponseEntity<String> exit(@PathVariable Long vehicleId) {
        yardService.exitVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle exited successfully");
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> yardVehicles() {
        return ResponseEntity.ok(vehicleService.getYardVehicles());
    }
}



