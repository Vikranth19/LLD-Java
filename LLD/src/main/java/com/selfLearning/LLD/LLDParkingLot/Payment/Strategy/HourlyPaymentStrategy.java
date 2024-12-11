package com.selfLearning.LLD.LLDParkingLot.Payment.Strategy;


import com.selfLearning.LLD.LLDParkingLot.Ticket.Ticket;

import java.time.temporal.ChronoUnit;

public class HourlyPaymentStrategy extends PaymentStrategy{

    public HourlyPaymentStrategy(){
        this.setBikeCharges(10);
        this.setCarCharges(20);
    }

    @Override
    public double calculateCost(Ticket ticket) {
        long hours = ChronoUnit.HOURS.between(ticket.getEntryTime(), ticket.getExitTime());
        return getChargesBasedOnType(ticket) * hours;
    }
}
