package com.ssorg.booking.service;

import com.ssorg.booking.dto.BookingRequest;
import com.ssorg.booking.model.Booking;
import com.ssorg.booking.model.Screen;
import com.ssorg.booking.model.Theatre;
import com.ssorg.booking.service.interfaces.BookingService;
import com.ssorg.booking.service.interfaces.TheatreService;

import java.util.List;
import java.util.stream.Collectors;

public class InMemoryBookingService implements BookingService {

    private TheatreService theatreService;


    public InMemoryBookingService(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @Override
    public Booking bookTicket(BookingRequest bookingRequest) {
       Theatre theatre = theatreService.listTheatres().stream()
                .filter(th -> th.getId().equals(bookingRequest.getTheatreId()))
                        .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Not a valid input"));

        Screen screen = theatreService.getScreens(theatre.getId())
                                    .stream()
                                    .filter(sc -> sc.getId().equals(bookingRequest.getScreenId()))
                                    .findFirst()
                                            .orElseThrow(() -> new IllegalArgumentException("not a valid input"));
        theatreService.reserveSeat(bookingRequest.getTheatreId(), bookingRequest.getScreenId(), bookingRequest.getShowId(), bookingRequest.getSeatRowIds(), bookingRequest.getSeatIds(), bookingRequest.getUser());
        return new Booking(theatre.getName(), screen.getName(), screen.getMovie().getMovieName(), bookingRequest.getCustomers().stream().map(el -> el).collect(Collectors.toList()));
    }

    @Override
    public Booking retrieve(String bookingId) {
        return null;
    }
}
