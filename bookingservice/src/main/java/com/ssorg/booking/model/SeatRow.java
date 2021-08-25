package com.ssorg.booking.model;

import java.util.List;
import java.util.UUID;

public class SeatRow {
    private String id;
    private String name;
    private List<Seat> seats;

    public SeatRow(String name, List<Seat> seats) {
        this.name = name;
        this.seats = seats;
        id = UUID.randomUUID().toString();
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getId() {
        return id;
    }
}
