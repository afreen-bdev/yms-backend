package com.yms.service;

import com.yms.dto.VehicleRequest;
import com.yms.entity.Vehicle;

public interface VehicleService {
    Vehicle registerVehicle(VehicleRequest request);
}
