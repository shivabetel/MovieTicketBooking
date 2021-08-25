package com.ssorg.booking.service.interfaces;

import com.ssorg.booking.dto.BookingRequest;
import com.ssorg.booking.model.Booking;

public interface BookingService {

    Booking bookTicket(BookingRequest bookingRequest);

    Booking retrieve(String bookingId);
}
