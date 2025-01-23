package com.selfLearning.LLD.LLDBookMyShow;

import lombok.Data;

import java.util.List;

@Data
public class Screen {
    int id;
    List<Seat> seats;
}
