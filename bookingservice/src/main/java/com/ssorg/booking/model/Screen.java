package com.ssorg.booking.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


public class Screen {
    private String id;
    private String name;
    private List<SeatRow> seatRows;
    private Movie movie;
    private List<Show> shows;

    public Map<String, Show> getShowMap() {
        return showMap;
    }

    private Map<String, Show> showMap;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SeatRow> getSeatRows() {
        return seatRows;
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen(String name, List<SeatRow> seatRows, List<Show> shows) {
        this.name = name;
        this.seatRows = seatRows;
        this.shows = shows;
        this.id= UUID.randomUUID().toString();
        this.showMap = Collections.unmodifiableMap(this.shows.stream().collect(Collectors.toMap(show -> show.getId(), show -> show)));
    }
}
