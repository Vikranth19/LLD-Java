package com.selfLearning.LLD.LLDParkingLot.Parking;


import com.selfLearning.LLD.LLDParkingLot.Gate.EntranceGate;
import com.selfLearning.LLD.LLDParkingLot.Gate.ExitGate;
import com.selfLearning.LLD.LLDParkingLot.Parking.Strategy.ParkingStrategy;
import com.selfLearning.LLD.LLDParkingLot.Vehicle.VehicleType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Singleton class
@Data
public class ParkingLot {

    private String id;
    private List<Floor> floorList;
    private List<EntranceGate> entranceGates;
    private List<ExitGate> exitGates;
    private ParkingStrategy parkingStrategy;

    public static ParkingLot INSTANCE = new ParkingLot();

    ParkingLot() {
        id = UUID.randomUUID().toString();
        floorList = new ArrayList<>();
        entranceGates = new ArrayList<>();
        exitGates = new ArrayList<>();
    }

    public boolean isParkingSlotAvailable(VehicleType type){
        for(Floor floor: floorList){
            if(floor.isParkingSlotAvailable(type)){
                return true;
            }
        }
        return false;
    }

    public ParkingSlot getParkingSlot(VehicleType type){
        for(Floor floor: floorList){
            if(floor.isParkingSlotAvailable(type)){
                return floor.getAvailableParkingSlot(type);
            }
        }

        return null;
    }
}
