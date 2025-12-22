package com.yms.service.impl;

import org.springframework.stereotype.Service;

import com.yms.entity.Vehicle;
import com.yms.entity.YardSlot;
import com.yms.repository.VehicleRepository;
import com.yms.repository.YardSlotRepository;
import com.yms.service.YardService;

@Service
public class YardServiceImpl implements YardService {

    private final VehicleRepository vehicleRepository;
    private final YardSlotRepository yardSlotRepository;

    public YardServiceImpl(VehicleRepository vehicleRepository,
                           YardSlotRepository yardSlotRepository) {
        this.vehicleRepository = vehicleRepository;
        this.yardSlotRepository = yardSlotRepository;
    }

    @Override
    public Vehicle allocateToYard(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        YardSlot slot = yardSlotRepository.findFirstByOccupiedFalse()
                .orElseThrow(() -> new RuntimeException("No free yard slots"));

        slot.setOccupied(true);
        vehicle.setYardSlot(slot);
        vehicle.setStatus("IN_YARD");

        yardSlotRepository.save(slot);
        return vehicleRepository.save(vehicle);
    }
}