package com.selfLearning.LLD.LLDCarRentalSystem;

import lombok.Getter;

@Getter
public class Car {

    private final String brand;
    private final String model;
    private final String numberPlate;
    private final double dailyRentalPrice;
    private final SeaterType seaterType;
    private boolean bookedStatus;

    public Car(String brand, String model, String numberPlate, double dailyRentalPrice, SeaterType seaterType){
        this.brand = brand;
        this.model = model;
        this.numberPlate = numberPlate;
        this.dailyRentalPrice = dailyRentalPrice;
        this.seaterType = seaterType;
        this.bookedStatus = false;
    }

    public void setAvailable(boolean bookedStatus){
        this.bookedStatus = bookedStatus;
    }

}
