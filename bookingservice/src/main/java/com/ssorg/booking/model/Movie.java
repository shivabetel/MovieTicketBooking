package com.ssorg.booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Movie {
    private String movieName;
    private String id;

    public Movie(String movieName) {
        this.movieName = movieName;
        this.id = UUID.randomUUID().toString();
    }
}
