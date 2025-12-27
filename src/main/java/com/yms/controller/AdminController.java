package com.yms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.yms.entity.Receipt;
import com.yms.entity.Vehicle;
import com.yms.repository.ReceiptRepository;
import com.yms.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final ReceiptRepository receiptRepository;

    public AdminController(AdminService adminService,
                           ReceiptRepository receiptRepository) {
        this.adminService = adminService;
        this.receiptRepository = receiptRepository;
    }

    // ---------------- VEHICLES ----------------

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(adminService.getAllVehicles());
    }

    @GetMapping("/vehicles/status/{status}")
    public ResponseEntity<List<Vehicle>> getVehiclesByStatus(
            @PathVariable String status) {
        return ResponseEntity.ok(
            adminService.getVehiclesByStatus(status)
        );
    }

    @GetMapping("/export/vehicles")
    public void exportVehicles(HttpServletResponse response)
            throws IOException {
        adminService.exportVehiclesToCsv(response);
    }
    
    @GetMapping("/revenue")
    public Double getTotalRevenue() {
        return receiptRepository.getTotalRevenue();
    }


    // ---------------- RECEIPTS ----------------

    @GetMapping("/receipts")
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @GetMapping(value = "/receipts/export", produces = "text/csv")
    public void exportReceipts(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader(
            "Content-Disposition",
            "attachment; filename=yard-receipts.csv"
        );

        PrintWriter writer = response.getWriter();
        writer.println("Vehicle,Driver,Hours,Amount,PaymentStatus");

        for (Receipt r : receiptRepository.findAll()) {
            writer.println(
                r.getVehicleNumber() + "," +
                r.getDriverName() + "," +
                r.getDurationInHours() + "," +
                r.getTotalAmount() + "," +
                r.getPaymentStatus()
            );
        }

        writer.flush();
    }
}
