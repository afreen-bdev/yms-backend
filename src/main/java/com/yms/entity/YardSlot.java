package com.yms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "yard_slots")
public class YardSlot {

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

    @Column(unique = true, nullable = false)
    private String slotNumber;

    @Column(nullable = false)
    private boolean occupied;

    // --- getters & setters ---

    public Long getId() {
        return id;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
