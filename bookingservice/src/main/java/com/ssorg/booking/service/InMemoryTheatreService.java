package com.ssorg.booking.service;

import com.ssorg.booking.constants.Theatres;
import com.ssorg.booking.model.Movie;
import com.ssorg.booking.model.Screen;
import com.ssorg.booking.model.Theatre;
import com.ssorg.booking.service.interfaces.MovieService;
import com.ssorg.booking.service.interfaces.TheatreService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryTheatreService implements TheatreService {
    private Map<String, Theatre> theatreMap;
    private Map<String, Screen> screenMap = new ConcurrentHashMap<>();
    private Map<String, List<Theatre>> movieToTheatreMap;
    private MovieService movieService;


    public InMemoryTheatreService(MovieService movieService) {
        this.movieService = movieService;
        theatreMap = Stream.of(Theatres.values())
                .map(theatre -> theatre.getConstructor().get().create(theatre.getTheatreName(), movieService.listMovies()))
                .collect(Collectors.toMap(theatre -> theatre.getId(), theatre -> theatre));
        theatreMap.forEach((key, value) -> value.getScreens().forEach(screen ->  screenMap.putIfAbsent(screen.getId(), screen)));
    }


    @Override
    public List<Screen> getScreens(String theatreId){
        return this.theatreMap.containsKey(theatreId) ? this.theatreMap.get(theatreId).getScreens() : Collections.emptyList();
    }


    @Override
    public void updateScreenMovie(String movieId, String theaterId, String screenId){

        this.theatreMap.computeIfPresent(theaterId, (key, value) -> {
            Movie movie = this.movieService.getMovieInfo(movieId);
            value.getMovies().add(movie);
            screenMap.computeIfPresent(screenId, (s, sv) -> {
                sv.setMovie(movie);
                return sv;
            });
            return value;
        });

    }


    @Override
    public List<Theatre> listTheatres() {
        return theatreMap.values().stream().collect(Collectors.toList());
    }


    @Override
    public List<Theatre> getTheatreByMovie(String movieId) {
        return theatreMap.values()
                .stream()
                .filter(theatre -> theatre.getMovies().stream()
                                 .filter(movie -> movie.getId().equals(movieId))
                                .collect(Collectors.toList())
                                .size() > 0)
                .collect(Collectors.toList());
    }
}
