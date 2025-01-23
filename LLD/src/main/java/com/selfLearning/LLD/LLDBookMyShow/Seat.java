package com.selfLearning.LLD.LLDBookMyShow;

import com.selfLearning.LLD.LLDBookMyShow.Enum.SeatCategory;
import lombok.Data;

@Data
public class Seat {
    int id;
    int row;
    SeatCategory seatCategory;
}
