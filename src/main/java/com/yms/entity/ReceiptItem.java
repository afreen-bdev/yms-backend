package com.yms.entity;

import jakarta.persistence.*;

@Entity
public class ReceiptItem {

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

	    private String description;

	    @Column(nullable = false)
	    private double price;

	    @Column(nullable = false)
	    private double amount;

	    @Column(nullable = false)
	    private int quantity;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	  public double getPrice() {
        return amount;
    }

    public void setPrice(double amount) {
        this.amount = amount;;
    }
}
