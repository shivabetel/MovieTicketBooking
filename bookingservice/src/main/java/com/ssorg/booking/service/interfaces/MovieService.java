package com.ssorg.booking.service.interfaces;

import com.ssorg.booking.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> listMovies();

    Movie getMovieInfo(String movieId);
}
