package com.hyperskill.cinemaRoom;

import java.util.List;

public class Stats {

    private int income;
    private int available;
    private int purchased;

    public Stats() {
    }

    public Stats(int income, int available, int purchased) {
        this.income = income;
        this.available = available;
        this.purchased = purchased;
    }

    public Stats(Cinema cinema) {
        List<Seat> seats = cinema.getSeats();
        int seatsTotal = seats.size();
        int availableSeats = 0;
        int amount = 0;
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                availableSeats += 1;
            } else {
                amount += seat.getPrice();
            }
        }
        this.income = amount;
        this.available = availableSeats;
        this.purchased = seatsTotal - availableSeats;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }
}
