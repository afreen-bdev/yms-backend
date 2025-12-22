package com.yms.service;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import com.yms.entity.Vehicle;

public interface AdminService {

    List<Vehicle> getAllVehicles();

    List<Vehicle> getVehiclesByStatus(String status);

    void exportVehiclesToCsv(HttpServletResponse response) throws IOException;
}