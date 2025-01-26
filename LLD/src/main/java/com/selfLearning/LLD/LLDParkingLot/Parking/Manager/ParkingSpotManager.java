package com.selfLearning.LLD.LLDParkingLot.Parking.Manager;


import com.selfLearning.LLD.LLDParkingLot.Gate.EntranceGate;
import com.selfLearning.LLD.LLDParkingLot.Gate.ExitGate;
import com.selfLearning.LLD.LLDParkingLot.Parking.Floor;
import com.selfLearning.LLD.LLDParkingLot.Parking.ParkingLot;
import com.selfLearning.LLD.LLDParkingLot.Parking.ParkingSlot;
import com.selfLearning.LLD.LLDParkingLot.Parking.Strategy.ParkingStrategy;
import lombok.Data;

import java.util.UUID;

@Data
public class ParkingSpotManager {

    private final String id;

    public ParkingSpotManager() {
        this.id = UUID.randomUUID().toString();
    }

    ParkingLot parkingLot = ParkingLot.getInstance();

    public void addFloor(Floor floor) {
        parkingLot.getFloorList().add(floor);
    }

    public void addSlot(Floor f, ParkingSlot s) {
        f.addSlot(s);
    }

    public void addEntranceGate(EntranceGate g) {
        parkingLot.getEntranceGates().add(g);
    }


    public void addExitGate(ExitGate g) {
        parkingLot.getExitGates().add(g);
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy){
        parkingLot.setParkingStrategy(parkingStrategy);
    }

}
