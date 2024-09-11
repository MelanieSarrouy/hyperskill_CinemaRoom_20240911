package com.hyperskill.cinemaRoom;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;
    private int price;

    @JsonIgnore
    private boolean available = true;

    public Seat() {}
    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row > 4 ? 8 : 10;
        this.available = true;
    }

    public Seat(int row, int column, int price, boolean available) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.available = available;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
