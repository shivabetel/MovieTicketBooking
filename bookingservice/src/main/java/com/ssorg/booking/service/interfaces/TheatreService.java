package com.ssorg.booking.service.interfaces;

import com.ssorg.booking.model.Movie;
import com.ssorg.booking.model.Screen;
import com.ssorg.booking.model.Theatre;

import java.util.List;

public interface TheatreService {

    List<Screen> getScreens(String theatreId);

    void updateScreenMovie(String movieId, String theaterId, String screenId);

    List<Theatre> listTheatres();

    List<Theatre> getTheatreByMovie(String movieId);

    void reserveSeat(String theatreId, String screenId, String showId, List<String> seatRows, List<String> seatIds, String user);

}
