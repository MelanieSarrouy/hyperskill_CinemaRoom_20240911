package com.hyperskill.cinemaRoom;

import java.util.UUID;

public class Ticket {

    private UUID token;
    private Seat ticket;

    public Ticket() {
    }

    public Ticket(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"ticket\": {\n" +
                "        \"row\": " + ticket.getRow() + ",\n" +
                "        \"column\": " + ticket.getColumn() + ",\n" +
                "        \"price\": " + ticket.getPrice() + "\n" +
                "    }\n" +
                "}";
    }

}
