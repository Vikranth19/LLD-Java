package com.selfLearning.LLD.LLDBookMyShow;

import com.selfLearning.LLD.LLDBookMyShow.Enum.City;
import lombok.Data;

import java.util.List;

@Data
public class Theatre {
    int id;
    String address;
    City city;

    List<Show> showList;
    List<Screen> screenList;
}
