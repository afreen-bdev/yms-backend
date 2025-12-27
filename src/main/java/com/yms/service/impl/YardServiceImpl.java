package com.yms.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yms.entity.Receipt;
import com.yms.entity.ReceiptItem;
import com.yms.entity.Vehicle;
import com.yms.entity.VehicleStatus;
import com.yms.entity.YardSlot;
import com.yms.repository.ReceiptRepository;
import com.yms.repository.VehicleRepository;
import com.yms.repository.YardSlotRepository;
import com.yms.service.YardService;

@Service
@Transactional
public class YardServiceImpl implements YardService {

    private final VehicleRepository vehicleRepository;
    private final YardSlotRepository yardSlotRepository;
    private final ReceiptRepository receiptRepository;

    public YardServiceImpl(
            VehicleRepository vehicleRepository,
            YardSlotRepository yardSlotRepository,
            ReceiptRepository receiptRepository) {

        this.vehicleRepository = vehicleRepository;
        this.yardSlotRepository = yardSlotRepository;
        this.receiptRepository = receiptRepository;
    }

    // ==========================
    // ALLOCATE VEHICLE TO YARD
    // ==========================
    @Override
    public void allocateVehicle(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (vehicle.getStatus() != VehicleStatus.REGISTERED) {
            throw new RuntimeException("Vehicle already allocated or exited");
        }

        YardSlot slot = yardSlotRepository
                .findFirstByOccupiedFalse()
                .orElseThrow(() -> new RuntimeException("No free yard slots"));

        slot.setOccupied(true);
        yardSlotRepository.save(slot);

        vehicle.setYardSlot(slot);
        vehicle.setStatus(VehicleStatus.IN_YARD);
        vehicleRepository.save(vehicle);
    }

    // ==========================
    // EXIT VEHICLE + BILLING
    // ==========================
    @Override
    @Transactional
    public void exitVehicle(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (vehicle.getYardSlot() == null) {
            throw new IllegalStateException(
                    "Vehicle is not allocated to any yard slot"
            );
        }

        // 1. FREE SLOT
        YardSlot slot = vehicle.getYardSlot();
        slot.setOccupied(false);
        yardSlotRepository.save(slot);

        // 2. UPDATE VEHICLE
        LocalDateTime exitTime = LocalDateTime.now();
        vehicle.setExitTime(exitTime);
        vehicle.setStatus(VehicleStatus.EXITED);
        vehicle.setYardSlot(null);
        vehicleRepository.save(vehicle);

        // 3. BILLING CALCULATION
        long hours = Duration.between(
                vehicle.getEntryTime(),
                exitTime
        ).toHours();

        if (hours <= 0) {
            hours = 1;
        }

        double amount = hours * 100; // DEMO RATE ₹100/hr

        // 4. RECEIPT ITEM
        ReceiptItem parking = new ReceiptItem();
        parking.setDescription("Yard parking charges");
        parking.setPrice(amount); 
        parking.setAmount(amount);
        parking.setQuantity(1);

        // 5. RECEIPT
        Receipt receipt = new Receipt();
        receipt.setVehicleNumber(vehicle.getVehicleNumber());
        receipt.setDriverName(vehicle.getDriverName());
        receipt.setEntryTime(vehicle.getEntryTime());
        receipt.setExitTime(exitTime);
        receipt.setDurationInHours(hours);
        receipt.setTotalAmount(amount);
        receipt.setPaymentStatus("PAID"); // Admin pays driver
        receipt.setItems(List.of(parking));

        receiptRepository.save(receipt);
    }
}
