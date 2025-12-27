package com.yms.service;

import java.util.List;

import com.yms.dto.VehicleRequest;
import com.yms.entity.Vehicle;

public interface VehicleService {
    Vehicle registerVehicle(VehicleRequest request);
    List<Vehicle> getYardVehicles();

}
