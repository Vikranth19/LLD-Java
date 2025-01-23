package com.selfLearning.LLD.LLDBookMyShow;

import lombok.Data;

import java.util.List;

@Data
public class Booking {
    int id;
    Show show;
    List<Seat> bookedSeats;
    Payment payment;
}
