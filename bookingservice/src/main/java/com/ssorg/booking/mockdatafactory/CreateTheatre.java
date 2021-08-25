package com.ssorg.booking.mockdatafactory;

import com.ssorg.booking.model.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class CreateTheatre {

    public Theatre create(String theatreName, List<Movie> movies){
        return new Theatre(theatreName, this.createScreen(), movies);
    }


    protected List<Screen> createScreen(){
        return Collections.emptyList();
    }

    protected List<SeatRow> createSeatRows(){
        return Collections.emptyList();
    }

    protected List<Seat> createSeats(){
        return Collections.emptyList();
    }

    protected List<Show> createShows() {
        return List.of(
                new Show(new Date(), "10:30 AM"),
                new Show(new Date(), "1:30 PM"),
                new Show(new Date(), "4:30 PM"),
                new Show(new Date(), "9:30 PM"));
    }
}
