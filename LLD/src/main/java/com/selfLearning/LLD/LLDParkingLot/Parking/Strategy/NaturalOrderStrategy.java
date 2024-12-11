package com.selfLearning.LLD.LLDParkingLot.Parking.Strategy;


import com.selfLearning.LLD.LLDParkingLot.Parking.ParkingSlot;
import com.selfLearning.LLD.LLDParkingLot.Vehicle.Vehicle;

import java.util.List;

public class NaturalOrderStrategy extends ParkingStrategy{

    @Override
    public void park(Vehicle vehicle) {
        System.out.println("Parked in natural order");
    }

    @Override
    public ParkingSlot park(List<ParkingSlot> slots) {
        return slots.getFirst();
    }
}
