package com.ssorg.booking.service;

import com.ssorg.booking.mockdatafactory.MovieData;
import com.ssorg.booking.model.Movie;
import com.ssorg.booking.service.interfaces.MovieService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryMovieService implements MovieService {

    private List<Movie> movies;

    private Map<String, Movie> movieMap;

    public InMemoryMovieService(MovieData movieData) {
        this.movies = movieData.getMovies();
        movieMap = this.movies.stream().collect(Collectors.toMap(Movie::getId, movie -> movie));
    }


    public Map<String, Movie> getMovieMap() {
        return movieMap;
    }

    @Override
    public List<Movie> listMovies() {
        return this.movies;
    }

    @Override
    public Movie getMovieInfo(String movieId) {
        return this.movieMap.get(movieId);
    }
}
