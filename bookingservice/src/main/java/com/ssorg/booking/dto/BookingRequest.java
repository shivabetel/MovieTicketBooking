package com.ssorg.booking.dto;

import java.util.ArrayList;
import java.util.List;

public class BookingRequest {

    private List<String> customers;
    private String movieId;
    private String theatreId;
    private String screenId;
    private String showId;
    private List<String> seatRowIds;
    private List<String> seatIds;
    private String alternateMobileNo;
    private String alternateEmailId;
    private String user;

    public String getShowId() {
        return showId;
    }

    public String getUser() {
        return user;
    }

    public BookingRequest(List<String> customers, String movieId, String theatreId, String screenId, String showId, List<String> seatRowIds,
                          List<String> seatIds, String alternateMobileNo, String alternateEmailId, String user) {
        this.customers = customers;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.screenId = screenId;
        this.showId = showId;
        this.seatRowIds = seatRowIds;
        this.seatIds = seatIds;
        this.alternateMobileNo = alternateMobileNo;
        this.alternateEmailId = alternateEmailId;
        this.user = user;
    }

    public List<String> getCustomers() {
        return customers;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public String getScreenId() {
        return screenId;
    }

    public List<String> getSeatRowIds() {
        return seatRowIds;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public String getAlternateMobileNo() {
        return alternateMobileNo;
    }

    public String getAlternateEmailId() {
        return alternateEmailId;
    }
}
