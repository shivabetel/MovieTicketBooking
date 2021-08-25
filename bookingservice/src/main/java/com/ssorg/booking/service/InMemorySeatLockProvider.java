package com.ssorg.booking.service;

import com.ssorg.booking.model.Screen;
import com.ssorg.booking.model.Seat;
import com.ssorg.booking.model.SeatLock;
import com.ssorg.booking.model.Show;
import com.ssorg.booking.service.interfaces.SeatLockProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySeatLockProvider implements SeatLockProvider {

    private Map<Screen, Map<Show, Map<Seat, SeatLock>>> seatLockMap = new ConcurrentHashMap<>();

    @Override
    public synchronized void acquireSeatLock(Screen screen, Show show, Seat seat, String user) {
        if(this.contains(screen, show, seat)){
            throw new RuntimeException("Seat already locked");//TODO create specific exception class instead of RuntimeException
        }else{
            seatLockMap.putIfAbsent(screen, new HashMap<>());
            seatLockMap.computeIfPresent(screen, (key, value) -> {
                value.putIfAbsent(show, new HashMap<>());
                value.computeIfPresent(show, (showKey, showValue) -> {
                    if(showValue.containsKey(seat)){
                        SeatLock seatLock = showValue.get(seat);
                        if(seatLock.isExpired()){
                            showValue.replace(seat, seatLock, new SeatLock(seat, user));
                        }
                    }else {
                        showValue.put(seat, new SeatLock(seat, user));
                    }
                    return  showValue;
                });

                return value;
            });
        }
    }

    @Override
    public void releaseSeatLock(Screen screen, Show show, Seat seat) {
        seatLockMap.computeIfPresent(screen, (key,value) -> {
            value.computeIfPresent(show, (showKey, showValue) -> {
                showValue.remove(seat);
                return showValue;
            });
            return  value;
        });
    }

    private boolean contains(Screen screen, Show show, Seat seat) {
        return seatLockMap.containsKey(screen) && seatLockMap.get(screen).containsKey(show) && !seatLockMap.get(screen).get(show).get(seat).isExpired();
    }
}
