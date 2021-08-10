package com.ssorg.booking.model;

import java.util.UUID;

public class Seat {
    private String id;
    private int seatNo;

    public Seat(int seatNo) {
        this.seatNo = seatNo;
        id = UUID.randomUUID().toString();
    }
}
