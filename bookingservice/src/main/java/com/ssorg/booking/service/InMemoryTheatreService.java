package com.ssorg.booking.service;

import com.ssorg.booking.constants.Theatres;
import com.ssorg.booking.model.*;
import com.ssorg.booking.service.interfaces.MovieService;
import com.ssorg.booking.service.interfaces.SeatLockProvider;
import com.ssorg.booking.service.interfaces.TheatreService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryTheatreService implements TheatreService {
    private Map<String, Theatre> theatreMap;
    private Map<String, Screen> screenMap = new ConcurrentHashMap<>();
    private Map<String, List<Theatre>> movieToTheatreMap;
    private MovieService movieService;
    private SeatLockProvider seatLockProvider;


    public InMemoryTheatreService(MovieService movieService, SeatLockProvider seatLockProvider) {
        this.movieService = movieService;
        this.seatLockProvider = seatLockProvider;
        theatreMap = Stream.of(Theatres.values())
                .map(theatre -> theatre.getConstructor().get().create(theatre.getTheatreName(), new ArrayList<>()))//movieService.listMovies()
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

    @Override
    public void reserveSeat(String theatreId, String screenId, String showId, List<String> seatRows, List<String> seatIds, String user) {
        this.screenMap.computeIfPresent(screenId, (key, screen) -> {
            List<SeatRow> selectedSeatRows = screen.getSeatRows().stream().filter(seatRow -> seatRows.indexOf(seatRow.getId()) != -1)
                            .collect(Collectors.toList());
            if(selectedSeatRows != null && selectedSeatRows.size() != 0){
                selectedSeatRows.stream().forEach(seatRow -> {
                    Seat selectedSeat = seatRow
                            .getSeats()
                            .stream()
                            .filter(seat -> seatIds.indexOf(seat.getId()) != -1)
                            .collect(Collectors.toList())
                            .get(0);
                    if(selectedSeat != null){
                        Show selectedShow = screen.getShowMap().get(showId);

                        this.seatLockProvider.acquireSeatLock(screen, selectedShow, selectedSeat, user);
//                        if(selectedSeat.isLocked())
//                        selectedSeat.setLocked(Boolean.TRUE);
//                        else{
//                            throw new RuntimeException("Seat not available");
//                        }
                    }
                });
            }

          return screen;
        });
    }
}
