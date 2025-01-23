package com.selfLearning.LLD.LLDBookMyShow;

import com.selfLearning.LLD.LLDBookMyShow.Enum.City;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MovieController {

    Map<City, List<Movie>> cityMovieMap;
    List<Movie> allMovies;

     public MovieController() {
        cityMovieMap = new HashMap<>();
        allMovies = new ArrayList<>();
    }

    //CRUD

    //ADD movie to a particular city, make use of cityVsMovies map
    void addMovie(Movie movie, City city) {

        allMovies.add(movie);

        List<Movie> movies = cityMovieMap.getOrDefault(city, new ArrayList<>());
        movies.add(movie);
        cityMovieMap.put(city, movies);
    }

    Movie getMovieByName(String movieName){
        for(Movie movie : allMovies){
            if(movie.getName().equals(movieName)){
                return movie;
            }
        }

        return null;
    }

    List<Movie> getMoviesByCity(City city){
        return cityMovieMap.get(city);
    }
}
