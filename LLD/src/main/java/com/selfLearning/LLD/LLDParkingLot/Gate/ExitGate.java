package com.selfLearning.LLD.LLDParkingLot.Gate;


import com.selfLearning.LLD.LLDParkingLot.Parking.ParkingSlot;
import com.selfLearning.LLD.LLDParkingLot.Payment.Payment;
import com.selfLearning.LLD.LLDParkingLot.Payment.PaymentType;
import com.selfLearning.LLD.LLDParkingLot.Payment.Strategy.PaymentStrategy;
import com.selfLearning.LLD.LLDParkingLot.Ticket.Ticket;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExitGate {

    private int id;

    public ExitGate(int id){
        this.id = id;
    }

    public Payment processPayment(PaymentType paymentType, Ticket ticket, PaymentStrategy paymentStrategy) {
        ticket.setExitTime(LocalDateTime.now().plusHours(2));
        double cost = paymentStrategy.calculateCost(ticket);
        ticket.setCharges(cost);
        Payment p = new Payment(cost,ticket,paymentStrategy);
        p.makePayment(paymentType);

        freeParkingSpot(ticket.getSlot());
        ticket.setActive(false);
        return p;
    }

    private void freeParkingSpot(ParkingSlot slot) {
        slot.isEmpty = true;
    }
}
