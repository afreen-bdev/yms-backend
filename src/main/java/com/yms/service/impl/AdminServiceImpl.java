package com.yms.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.yms.entity.Vehicle;
import com.yms.repository.VehicleRepository;
import com.yms.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    private final VehicleRepository vehicleRepository;

    public AdminServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getVehiclesByStatus(String status) {
        return vehicleRepository.findAll()
                .stream()
                .filter(v -> v.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    @Override
    public void exportVehiclesToCsv(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=vehicles.csv");

        PrintWriter writer = response.getWriter();
        writer.println("VehicleNumber,Type,Driver,Status,EntryTime,ExitTime,YardSlot");

        for (Vehicle v : vehicleRepository.findAll()) {
            writer.println(
                v.getVehicleNumber() + "," +
                v.getVehicleType() + "," +
                v.getDriverName() + "," +
                v.getStatus() + "," +
                v.getEntryTime() + "," +
                v.getExitTime() + "," +
                (v.getYardSlot() != null ? v.getYardSlot().getSlotNumber() : "")
            );
        }

        writer.flush();
    }
}