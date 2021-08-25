package com.ssorg.booking.model;

import java.util.Date;
import java.util.UUID;

public class Show {

    private String id;
    private Date showDate;
    private String showTime;

    public String getId() {
        return id;
    }

    public Show(Date showDate, String showTime) {
        this.id = UUID.randomUUID().toString();
        this.showDate = showDate;
        this.showTime = showTime;
    }
}
