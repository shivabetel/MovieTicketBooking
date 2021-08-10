package com.ssorg;

import com.ssorg.booking.mockdatafactory.MovieData;
import com.ssorg.booking.model.Movie;
import com.ssorg.booking.model.Screen;
import com.ssorg.booking.model.Theatre;
import com.ssorg.booking.service.InMemoryMovieService;
import com.ssorg.booking.service.InMemoryTheatreService;
import com.ssorg.booking.service.interfaces.MovieService;
import com.ssorg.booking.service.interfaces.TheatreService;

import java.util.*;
import java.util.Optional;

public class App {


    public static void main(String[] args) {
        MovieData movieData = new MovieData();
        MovieService movieService = new InMemoryMovieService(movieData);
        TheatreService theatreService = new InMemoryTheatreService(movieService);
        Optional<Movie> movie =  movieService.listMovies().stream().findAny();
//        List<Theatre> theatres = theatreService.getTheatreByMovie(movie.get().getId());
//        System.out.println("theatres::"+theatres.size());
        Optional<Theatre> theatre = theatreService.listTheatres().stream().findAny();
        Optional<Screen> optionalScreen = theatreService.getScreens(theatre.get().getId()).stream().findAny();
        theatreService.updateScreenMovie(movie.get().getId(), theatre.get().getId(), optionalScreen.get().getId());

        System.out.println("screen:"+theatre.get().getMovies().size());

    }
}
