package com.selfLearning.LLD.LLDParkingLot.Payment.Strategy;


import com.selfLearning.LLD.LLDParkingLot.Ticket.Ticket;

import java.time.temporal.ChronoUnit;

public class DailyPaymentStrategy extends PaymentStrategy {

    public DailyPaymentStrategy(){
        this.setBikeCharges(100);
        this.setCarCharges(200);
    }

    @Override
    public double calculateCost(Ticket ticket) {
        long days = ChronoUnit.DAYS.between(ticket.getEntryTime(),ticket.getExitTime());
        return getChargesBasedOnType(ticket) * days;
    }
}
