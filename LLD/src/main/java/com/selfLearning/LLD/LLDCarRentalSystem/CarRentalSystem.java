package com.selfLearning.LLD.LLDCarRentalSystem;

import com.selfLearning.LLD.LLDCarRentalSystem.Payment.PaymentProcessor;
import com.selfLearning.LLD.LLDCarRentalSystem.Payment.UpiPaymentProcessor;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarRentalSystem {

    private static CarRentalSystem instance;
    private final Map<String, List<Car>> carsByLocation;
    private final Map<String, Reservation> reservations;
    private final PaymentProcessor paymentProcessor;

    CarRentalSystem(){
        carsByLocation = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
        paymentProcessor = new UpiPaymentProcessor();
    }

    public static synchronized CarRentalSystem getInstance(){
        if(instance==null){
            return new CarRentalSystem();
        }
        return instance;
    }

    public void addCar(String location, Car car){
        carsByLocation.putIfAbsent(location, new ArrayList<>());
        carsByLocation.get(location).add(car);
    }

    public List<Car> searchCars(String location, String brand, String model, LocalDate start, LocalDate end){
        Stream<Car> carStream;
        if(!location.isEmpty()){
            carStream = carsByLocation.get(location).stream();
        } else{
            carStream = carsByLocation.entrySet().stream().flatMap(car -> car.getValue().stream());
        }

        if(brand!=null && !brand.isEmpty()){
            carStream = carStream.filter(car -> car.getBrand().equalsIgnoreCase(brand));
        }

        if(model!=null && !model.isEmpty()){
            carStream = carStream.filter(car -> car.getModel().equalsIgnoreCase(model));
        }

        return carStream.filter(car -> isAvailable(car, start, end)).collect(Collectors.toList());
    }

    private boolean isAvailable(Car car, LocalDate start, LocalDate end) {
       for(Reservation reservation : reservations.values()){
            if(reservation.getCar().equals(car)){
                if(start.isBefore(reservation.getEndDate()) && end.isAfter(reservation.getStartDate())){
                    return false;
                }
            }
       }

       return true;
    }

    public synchronized  Reservation makeReservation(Customer customer, Car car, LocalDate start, LocalDate end){
        if(isAvailable(car, start, end)){
            String resId = generateReservationId();
            Reservation reservation = new Reservation(resId, customer, car, start, end);
            reservations.put(resId, reservation);
            car.setAvailable(false);
            return reservation;
        }
        return null;
    }

    public synchronized void cancelReservation(String resId) {
        Reservation reservation = reservations.remove(resId);
        if(reservation!=null){
            reservation.getCar().setAvailable(true);
        }
    }

    public boolean processPayment(Reservation reservation) {
        return paymentProcessor.processPayment(reservation.getTotalPrice());
    }

    private String generateReservationId() {
        return "RES" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
