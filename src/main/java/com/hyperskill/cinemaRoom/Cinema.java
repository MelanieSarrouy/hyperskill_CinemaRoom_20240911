package com.hyperskill.cinemaRoom;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int rows;
    private int columns;
    private List<Seat> seats;

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = getAvailable_seats(rows, columns);
    }

    private List<Seat> getAvailable_seats(int rows, int columns) {
        List<Seat> list = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Seat seat = new Seat(i + 1, j + 1);
                list.add(seat);
            }
        }
        return list;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List seats) {
        this.seats = seats;
    }
}
