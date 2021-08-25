package com.ssorg.booking.mockdatafactory;

import com.ssorg.booking.model.Screen;
import com.ssorg.booking.model.Seat;
import com.ssorg.booking.model.SeatRow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Theatre3 extends CreateTheatre{


    @Override
    protected List<Screen> createScreen() {
        return Stream.of("Screen1", "Screen2", "Screen3", "Screen4")
                .map(this::_initScreen)
                .collect(Collectors.toList());
    }

    @Override
    protected List<SeatRow> createSeatRows() {
        return Stream.of("A","B","C","D","E","F","G","H","I","J","K","L")
                .map(this::_initSeatRow)
                .collect(Collectors.toList());
    }

    @Override
    protected List<Seat> createSeats() {
        return super.createSeats();
    }

    private  Screen _initScreen(String name){
        Screen screen = new Screen(
                name, _initSeatRows(), createShows());
        return screen;
    }

    private  List<SeatRow> _initSeatRows(){
        return Stream.of("A","B","C","D","E","F","G","H","I","J","K","L")
                .map(this::_initSeatRow)
                .collect(Collectors.toList());
    }

    private   SeatRow _initSeatRow(String name){
        SeatRow seatRow = new SeatRow("A", _initSeats());
        return seatRow;
    }

    private List<Seat> _initSeats(){
        return Stream.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
                .map(this::_initSeat)
                .collect(Collectors.toList());
    }

    private  Seat _initSeat(int seatNo){
        return new Seat(seatNo);
    }
}
