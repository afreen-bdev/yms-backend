package com.yms.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "yard_slot_id")
    private YardSlot yardSlot;
    
    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Receipt receipt;

    @Column(nullable = false)
    private String vehicleNumber;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    public Long getId() {
        return id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
    
    public YardSlot getYardSlot() {
        return yardSlot;
    }

    public void setYardSlot(YardSlot yardSlot) {
        this.yardSlot = yardSlot;
    }
    
    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

}
