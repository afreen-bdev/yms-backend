package com.yms.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yms.dto.VehicleRequest;
import com.yms.entity.Vehicle;
import com.yms.repository.VehicleRepository;
import com.yms.service.VehicleService;
import com.yms.entity.VehicleStatus;

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
        vehicle.setStatus(VehicleStatus.REGISTERED);

        return vehicleRepository.save(vehicle);
    }
    
    @Override
    public List<Vehicle> getYardVehicles() {
        return vehicleRepository.findByStatusIn(
                List.of(VehicleStatus.REGISTERED,
                		VehicleStatus.IN_YARD)
        );
    }

}
