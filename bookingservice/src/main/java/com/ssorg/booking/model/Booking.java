package com.ssorg.booking.model;

import java.util.List;
import java.util.UUID;

public class Booking {

    private String id;
    private String theatreName;
    private String screenName;
    private String movieName;
    private List<String> users;

    public Booking(String theatreName, String screenName, String movieName, List<String> users) {
        this.theatreName = theatreName;
        this.screenName = screenName;
        this.movieName = movieName;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getMovieName() {
        return movieName;
    }

    public List<String> getUsers() {
        return users;
    }
}
