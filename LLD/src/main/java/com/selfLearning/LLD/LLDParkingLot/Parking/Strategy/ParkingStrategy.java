package com.selfLearning.LLD.LLDParkingLot.Parking.Strategy;


import com.selfLearning.LLD.LLDParkingLot.Parking.ParkingSlot;
import com.selfLearning.LLD.LLDParkingLot.Vehicle.Vehicle;

import java.util.List;

public abstract class ParkingStrategy {
    public abstract void park(Vehicle vehicle);

    public abstract ParkingSlot park(List<ParkingSlot> slots);
}
