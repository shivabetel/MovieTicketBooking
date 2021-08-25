package com.ssorg;

import com.ssorg.booking.dto.BookingRequest;
import com.ssorg.booking.mockdatafactory.MovieData;
import com.ssorg.booking.model.*;
import com.ssorg.booking.service.InMemoryBookingService;
import com.ssorg.booking.service.InMemoryMovieService;
import com.ssorg.booking.service.InMemorySeatLockProvider;
import com.ssorg.booking.service.InMemoryTheatreService;
import com.ssorg.booking.service.interfaces.BookingService;
import com.ssorg.booking.service.interfaces.MovieService;
import com.ssorg.booking.service.interfaces.SeatLockProvider;
import com.ssorg.booking.service.interfaces.TheatreService;

import java.util.*;
import java.util.Optional;

public class App {


    public static void main(String[] args) {
        MovieData movieData = new MovieData();
        MovieService movieService = new InMemoryMovieService(movieData);
        SeatLockProvider seatLockProvider = new InMemorySeatLockProvider();
        TheatreService theatreService = new InMemoryTheatreService(movieService, seatLockProvider);
        BookingService bookingService = new InMemoryBookingService(theatreService);
        Optional<Movie> movie =  movieService.listMovies().stream().findAny();
//        List<Theatre> theatres = theatreService.getTheatreByMovie(movie.get().getId());
//        System.out.println("theatres::"+theatres.size());
        Optional<Theatre> theatre = theatreService.listTheatres().stream().findAny();
        Optional<Screen> optionalScreen = theatreService.getScreens(theatre.get().getId()).stream().findAny();
        Optional<SeatRow> optionalSeatRow = optionalScreen.get().getSeatRows().stream().findAny();
        Optional<Show> optionalShow = optionalScreen.get().getShowMap().values().stream().findAny();
        Optional<Seat> optionalSeat = optionalSeatRow.get().getSeats().stream().findAny();
        theatreService.updateScreenMovie(movie.get().getId(), theatre.get().getId(), optionalScreen.get().getId());

        System.out.println("screen:"+theatre.get().getMovies().size());
        BookingRequest bookingRequest = new BookingRequest(
                List.of("shivakumar"),
                movie.get().getId(),
                theatre.get().getId(),
                optionalScreen.get().getId(),
                optionalShow.get().getId(),
                List.of(optionalSeatRow.get().getId()),
                List.of(optionalSeat.get().getId()),
                "8660036870",
                "shiva.betel@gmail.com",
                "shiva"

        );
        BookingRequest bookingRequest1 = new BookingRequest(
                List.of("shivakumarGM"),
                movie.get().getId(),
                theatre.get().getId(),
                optionalScreen.get().getId(),
                optionalShow.get().getId(),
                List.of(optionalSeatRow.get().getId()),
                List.of(optionalSeat.get().getId()),
                "8660036870",
                "shiva.betel@gmail.com",
                "ram"

        );
       Booking booking =  bookingService.bookTicket(bookingRequest);
        Booking booking1 =  bookingService.bookTicket(bookingRequest1);
        System.out.println(optionalSeat.get().isLocked());
        System.out.println(booking.getMovieName());


    }
}
