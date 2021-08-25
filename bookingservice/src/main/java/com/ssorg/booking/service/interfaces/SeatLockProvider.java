package com.ssorg.booking.service.interfaces;

import com.ssorg.booking.model.Screen;
import com.ssorg.booking.model.Seat;
import com.ssorg.booking.model.Show;

public interface SeatLockProvider {

    void acquireSeatLock(Screen screen, Show show, Seat seat, String user);
    void releaseSeatLock(Screen screen, Show show, Seat seat);
}
