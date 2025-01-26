package com.selfLearning.LLD.LLDParkingLot.Gate;


import com.selfLearning.LLD.LLDParkingLot.Parking.ParkingLot;
import com.selfLearning.LLD.LLDParkingLot.Parking.ParkingSlot;
import com.selfLearning.LLD.LLDParkingLot.Ticket.Ticket;
import com.selfLearning.LLD.LLDParkingLot.Vehicle.Vehicle;
import lombok.Data;

@Data
public class EntranceGate {

    private int id;

    public EntranceGate(int id){
        this.id = id;
    }

    public Ticket generateTicket(Vehicle vehicle){
        if(!ParkingLot.getInstance().isParkingSlotAvailable(vehicle.getVehicleType())){
            System.out.println("Parking is full");
            return null;
        }

        ParkingSlot slot =  ParkingLot.getInstance().getParkingSlot(vehicle.getVehicleType());

        return new Ticket(vehicle, slot);
    }
}
