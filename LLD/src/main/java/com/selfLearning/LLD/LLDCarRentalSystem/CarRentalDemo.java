package com.selfLearning.LLD.LLDCarRentalSystem;

import java.time.LocalDate;
import java.util.List;

public class CarRentalDemo {

    public static void main(String[] args) {
        CarRentalSystem rentalSystem = CarRentalSystem.getInstance();

        // Add cars to the rental system
        rentalSystem.addCar("Hyderabad", new Car("Toyota", "Camry", "ABC123", 50.0, SeaterType.SIX_SEATER));
        rentalSystem.addCar("Bangalore", new Car("Honda", "Civic", "XYZ789", 45.0, SeaterType.FOUR_SEATER));
        rentalSystem.addCar("Bangalore", new Car("Ford", "Mustang", "DEF456", 80.0, SeaterType.SIX_SEATER));

        Customer customer1 = new Customer("Kishore", "998781234", "RC-1234");

        //Search for cars
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);
        List<Car> availableCars = rentalSystem.searchCars("Bangalore", "Ford", "", startDate, endDate);

        if(!availableCars.isEmpty()){
            Car selectedCar = availableCars.getFirst();
            System.out.println("Car selected - " + selectedCar.getNumberPlate());
            Reservation reservation = rentalSystem.makeReservation(customer1, selectedCar, startDate, endDate);

            if(reservation!=null){
                boolean paymentSuccess = rentalSystem.processPayment(reservation);

                if(paymentSuccess){
                    System.out.println("Payment successful, reservation id : " + reservation.getReservationId());
                } else{
                    System.out.println("Payment unsuccessful, cancelling reservation : " + reservation.getReservationId());
                    rentalSystem.cancelReservation(reservation.getReservationId());
                }
            } else{
                System.out.println("Selected car is not available to book");
            }
        } else {
            System.out.println("No available cars found for the given criteria.");
        }
    }
}
