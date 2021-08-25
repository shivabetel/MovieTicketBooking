package com.ssorg.booking.model;

import java.util.UUID;

public class Seat {
    private String id;
    private int seatNo;

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    private boolean booked;
    private boolean locked;

    public Seat(int seatNo) {
        this.seatNo = seatNo;
        id = UUID.randomUUID().toString();
        booked = Boolean.FALSE;
        locked = Boolean.FALSE;
    }

    public String getId() {
        return id;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public boolean isBooked() {
        return booked;
    }

    public boolean isLocked() {
        return locked;
    }
}
