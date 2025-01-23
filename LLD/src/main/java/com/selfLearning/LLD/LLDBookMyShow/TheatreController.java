package com.selfLearning.LLD.LLDBookMyShow;

import com.selfLearning.LLD.LLDBookMyShow.Enum.City;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TheatreController {

    Map<City, List<Theatre>> cityTheatreMap;
    List<Theatre> allTheatres;

    public TheatreController() {
        cityTheatreMap = new HashMap<>();
        allTheatres = new ArrayList<>();
    }

    //CRUD

    void addTheatre(City city, Theatre theatre){
        allTheatres.add(theatre);

        List<Theatre> theatreList = cityTheatreMap.getOrDefault(city, new ArrayList<>());
        theatreList.add(theatre);
        cityTheatreMap.put(city, theatreList);
    }

    //In BMS when we select a city and a movie
    Map<Theatre, List<Show>> getAllShows(Movie movie, City city){
        List<Theatre> theatresInCity = cityTheatreMap.get(city);

        Map<Theatre, List<Show>> showsMap = new HashMap<>();

        for(Theatre theatre : theatresInCity){

            List<Show> givenMovieShows = new ArrayList<>();
            List<Show> allShowsInTheatre = theatre.getShowList();

            for(Show show : allShowsInTheatre){
                if(show.getMovie().getId() == movie.getId()){
                    givenMovieShows.add(show);
                }
            }
            if(!givenMovieShows.isEmpty())showsMap.put(theatre, givenMovieShows);
        }

        return showsMap;
    }
}
