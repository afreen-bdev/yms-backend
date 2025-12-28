package com.yms.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "receipt")
public class Receipt {

	@Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "seq_gen"
    )
    @SequenceGenerator(
        name = "seq_gen",
        sequenceName = "global_seq",
        allocationSize = 1
    )
    private Long id;

    private String receiptNumber;
    
    private String vehicleNumber;
    private String driverName;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    private long durationInHours;
    private double totalAmount;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    
    private String paymentStatus; // PAID / PENDING

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "receipt_id")
    private List<ReceiptItem> items;

    // getters and setters

    public Long getId() {
        return id;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
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

    public long getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(long durationInHours) {
        this.durationInHours = durationInHours;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public List<ReceiptItem> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }
}
