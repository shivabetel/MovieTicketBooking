package com.ssorg.booking.model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class Screen {
    private String id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SeatRow> getSeatRows() {
        return seatRows;
    }

    private String name;
    private List<SeatRow> seatRows;
    private Movie movie;


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen(String name, List<SeatRow> seatRows) {
        this.name = name;
        this.seatRows = Collections.unmodifiableList(seatRows);
        id = UUID.randomUUID().toString();
    }
}
