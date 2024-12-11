package com.selfLearning.LLD.LLDParkingLot.Parking;

import com.selfLearning.LLD.LLDParkingLot.Vehicle.Vehicle;
import lombok.Data;

@Data
public abstract class ParkingSlot {

    public String id;
    public boolean isEmpty;
    public SlotType type;
    public Vehicle vehicle;

    public ParkingSlot(String id, SlotType type){
        this.id = id;
        this.type = type;
        this.isEmpty = true;
        this.vehicle = null;
    }


    public ParkingSlot parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        isEmpty = false;
        return this;
    }

    public ParkingSlot removeVehicle(Vehicle vehicle){
        this.vehicle = null;
        isEmpty = true;
        return this;
    }
    
}
