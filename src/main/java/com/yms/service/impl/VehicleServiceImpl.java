package com.yms.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.yms.dto.VehicleRequest;
import com.yms.entity.Vehicle;
import com.yms.repository.VehicleRepository;
import com.yms.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle registerVehicle(VehicleRequest request) {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setDriverName(request.getDriverName());
        vehicle.setEntryTime(LocalDateTime.now());
        vehicle.setStatus("REGISTERED");

        return vehicleRepository.save(vehicle);
    }
}
