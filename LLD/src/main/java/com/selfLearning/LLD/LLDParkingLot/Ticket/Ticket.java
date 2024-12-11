package com.selfLearning.LLD.LLDParkingLot.Ticket;


import com.selfLearning.LLD.LLDParkingLot.Parking.ParkingSlot;
import com.selfLearning.LLD.LLDParkingLot.Vehicle.Vehicle;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Ticket {

    private String ticketId;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private ParkingSlot slot;
    private double charges;
    private boolean isActive;

    public Ticket(Vehicle vehicle,ParkingSlot slot){
        this.ticketId = UUID.randomUUID().toString();
        this.entryTime = LocalDateTime.now();
        this.vehicle = vehicle;
        this.slot = slot;
        this.isActive = true;
    }
}
