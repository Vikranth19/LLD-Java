package com.selfLearning.LLD.LLDBookMyShow;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Show {
    int id;
    Movie movie;
    Screen screen;
    int startTime;
    int endTime;
    List<Integer> bookedSeatIds = new ArrayList<>();
}
