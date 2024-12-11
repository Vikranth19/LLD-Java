package com.selfLearning.LLD.LLDParkingLot.Vehicle;

import lombok.Data;

@Data
public abstract class Vehicle {
    private String vehicleNumber;
    private VehicleType vehicleType;

    public Vehicle(String vehicleNumber, VehicleType vehicleType){
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }
}
