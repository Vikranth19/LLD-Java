package com.selfLearning.LLD.LLDParkingLot;


import com.selfLearning.LLD.LLDParkingLot.Gate.EntranceGate;
import com.selfLearning.LLD.LLDParkingLot.Gate.ExitGate;
import com.selfLearning.LLD.LLDParkingLot.Parking.*;
import com.selfLearning.LLD.LLDParkingLot.Parking.Manager.ParkingSpotManager;
import com.selfLearning.LLD.LLDParkingLot.Parking.Strategy.NaturalOrderStrategy;
import com.selfLearning.LLD.LLDParkingLot.Payment.Payment;
import com.selfLearning.LLD.LLDParkingLot.Payment.PaymentType;
import com.selfLearning.LLD.LLDParkingLot.Payment.Strategy.HourlyPaymentStrategy;
import com.selfLearning.LLD.LLDParkingLot.Ticket.Ticket;
import com.selfLearning.LLD.LLDParkingLot.Vehicle.Bike;
import com.selfLearning.LLD.LLDParkingLot.Vehicle.Car;
import com.selfLearning.LLD.LLDParkingLot.Vehicle.Vehicle;

public class Main {

    public static void main(String[] args){
        ParkingLot parkingLot = ParkingLot.INSTANCE;

        ParkingSpotManager manager = new ParkingSpotManager();

        Floor floor1 = new Floor("1");
        Floor floor2 = new Floor("2");
        Floor floor3 = new Floor("3");

        manager.addFloor(floor1); manager.addFloor(floor2); manager.addFloor(floor3);

        EntranceGate entranceGate = new EntranceGate(1);
        ExitGate exitGate = new ExitGate(2);

        manager.addEntranceGate(entranceGate); manager.addExitGate(exitGate);

        manager.setParkingStrategy(new NaturalOrderStrategy());

        ParkingSlot slot1 = new FourWheelerParkingSlot("id1");
        ParkingSlot slot2 = new TwoWheelerParkingSlot("id2");
        ParkingSlot slot3 = new TwoWheelerParkingSlot("id3");
        ParkingSlot slot4 = new FourWheelerParkingSlot("id4");

        manager.addSlot(floor1, slot1);
        manager.addSlot(floor2, slot2);
        manager.addSlot(floor3, slot3); manager.addSlot(floor3, slot4);


        System.out.println("Vehicle started coming in!!");
        Vehicle v1 = new Bike("AP09-2249");
        Vehicle v2 = new Car("AP49-5769");
        System.out.println("Car no. - " + v2.getVehicleNumber());
        boolean isAvailableForBike1 = parkingLot.isParkingSlotAvailable(v1.getVehicleType());

        System.out.println("Is parking slot available for Bike? - " + isAvailableForBike1);

        if(isAvailableForBike1){
            Ticket t1 = entranceGate.generateTicket(v1);
            System.out.println("Ticket details - " + t1.toString());

            System.out.println("----------------------------------");

            System.out.println("Exiting from parking lot");
            Payment p1 = exitGate.processPayment(PaymentType.UPI, t1, new HourlyPaymentStrategy());
            System.out.println("Payment details - " + p1.getAmount());
        }

    }
}
