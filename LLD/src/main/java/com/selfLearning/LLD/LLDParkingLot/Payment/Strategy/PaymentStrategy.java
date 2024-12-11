package com.selfLearning.LLD.LLDParkingLot.Payment.Strategy;

import com.selfLearning.LLD.LLDParkingLot.Ticket.Ticket;
import lombok.Data;

@Data
public abstract class PaymentStrategy {

    private double bikeCharges;
    private double carCharges;

    public abstract double calculateCost(Ticket ticket);

    public double getChargesBasedOnType(Ticket ticket){
        switch (ticket.getVehicle().getVehicleType()){
            case CAR -> {
                return carCharges;
            }
            case MOTORBIKE -> {
                return bikeCharges;
            }
            default -> {
                return 0;
            }
        }
    }
}
