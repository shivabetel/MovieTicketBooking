package com.ssorg.booking.mockdatafactory;

import com.ssorg.booking.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieData {

    private List<Movie> movies = List.of(
            new Movie("Avengers - EndGame"),
                new Movie("Sherlock Homes"),
                new Movie("Batman vs Superman")
        );


    public List<Movie> getMovies() {
        return new ArrayList<>(movies);
    }
}
