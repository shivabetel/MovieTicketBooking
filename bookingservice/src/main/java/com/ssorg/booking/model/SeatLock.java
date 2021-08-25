package com.ssorg.booking.model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class SeatLock {

    private String id;
    private Seat seat;
    private boolean isExpired;
    private String user;
    private long timeoutInMilliSeconds;


    public SeatLock() {
        this.id = UUID.randomUUID().toString();
        this.isExpired = Boolean.FALSE;
        this.timeoutInMilliSeconds = 600000;
        startTimer();
    }

    public SeatLock(Seat seat, String user, long timeoutInMilliSeconds) {
        this();
        this.seat = seat;
        this.user = user;
        this.timeoutInMilliSeconds = timeoutInMilliSeconds;
    }

    public SeatLock(Seat seat, String user) {
        this();
        this.seat = seat;
        this.user = user;

    }

    public boolean isExpired() {
        return isExpired;
    }

    private void startTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isExpired = Boolean.TRUE;
            }
        }, timeoutInMilliSeconds);
    }


}
