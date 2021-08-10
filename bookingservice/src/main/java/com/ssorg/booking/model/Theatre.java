package com.ssorg.booking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Setter
@Getter
public class Theatre {

    private String id;
    private String name;
    private List<Screen> screens;
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Screen> getScreens() {
        return Collections.unmodifiableList(screens);
    }

    public Theatre(String name, List<Screen> screens, List<Movie> movies) {
        this.name = name;
        this.screens = screens;
        this.movies = movies;
        id = UUID.randomUUID().toString();
    }
}
