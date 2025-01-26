package com.selfLearning.LLD.LLDParkingLot.Parking;

import com.selfLearning.LLD.LLDParkingLot.Vehicle.VehicleType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Floor {

    private final String floorId;
    private Map<SlotType, List<ParkingSlot>> slotsMap = new HashMap<>();

    public Floor(String floorId){
        this.floorId = floorId;
        slotsMap.put(SlotType.TWO,new ArrayList<>());
        slotsMap.put(SlotType.FOUR,new ArrayList<>());
    }

    public void addSlot(ParkingSlot slot) {
        slotsMap.get(slot.getType()).add(slot);
    }
    public void removeSlot(ParkingSlot slot){
        slotsMap.get(slot.getType()).remove(slot);
    }

    public ParkingSlot getAvailableParkingSlot(VehicleType vehicleType){
        List<ParkingSlot> availableSlots = new ArrayList<>();
        for(ParkingSlot p : slotsMap.get(mapVehicleToSlotType(vehicleType))) {
            if(p.isEmpty) {
                availableSlots.add(p);
            }
        }

        return ParkingLot.getInstance().getParkingStrategy().park(availableSlots);
    }

    public SlotType mapVehicleToSlotType(VehicleType vehicleType){
        switch (vehicleType) {
            case CAR -> {
                return SlotType.FOUR;
            }
            case MOTORBIKE -> {
                return SlotType.TWO;
            }
        }
        return null;
    }

    public boolean isParkingSlotAvailable(VehicleType type){
        for (ParkingSlot slot: slotsMap.get(mapVehicleToSlotType(type))){
            if(slot.isEmpty){
                return true;
            }
        }
        return false;
    }
}
