package com.hyperskill.cinemaRoom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class Controller {

    private Cinema cinema = new Cinema(9,9);
    private List<Seat> seatsList = cinema.getSeats();
    private ConcurrentMap<UUID, Ticket> tickets = new ConcurrentHashMap<>();

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @GetMapping("/seat")
    public ResponseEntity<?> getSeat(@RequestParam int row, @RequestParam int column) {
        int index = calculateSeatIndex(row, column);
        if (index >= 0 && index < seatsList.size()) {
            Seat seat = seatsList.get(index);
            return ResponseEntity.ok(seat);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("The number of a row or a column is out of bounds!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getSeat(@RequestParam(required = false) String password) {

        if (password == null || password.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("The password is wrong!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        if (password.equals("super_secret")) {
            Stats stats = new Stats(cinema);
            return ResponseEntity.ok(stats);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("The password is wrong!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> postPurchase(@RequestBody Seat seatInput) {
        int row = seatInput.getRow();
        int column = seatInput.getColumn();
        int index = calculateSeatIndex(row, column);
        if (index >= 0 && index < seatsList.size() && column <= cinema.getColumns() && row <= cinema.getRows() && row > 0 && column > 0) {
            Seat seat = seatsList.get(index);
            if (!seat.isAvailable()) {
                ErrorResponse errorResponse = new ErrorResponse("The ticket has been already purchased!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            } else {
                // Mark the seat as purchased
                seat.setAvailable(false);
                seatsList.set(index, seat);
                cinema.setSeats(seatsList);
                UUID uuid = UUID.randomUUID();
                Ticket ticket = new Ticket(uuid, seat);
                tickets.put(uuid, ticket);
                return ResponseEntity.ok(ticket);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse("The number of a row or a column is out of bounds!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> postReturn(@RequestBody Token tokenInput) {
        Ticket ticket = tickets.get(tokenInput.getToken());
        if (ticket != null) {
            Seat seat = ticket.getTicket();
            int row = seat.getRow();
            int column = seat.getColumn();
            int index = calculateSeatIndex(row, column);
            seat.setAvailable(true);
            seatsList.set(index, seat);
            cinema.setSeats(seatsList);
            tickets.remove(tokenInput.getToken());
            return ResponseEntity.ok(ticket.toString());
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Wrong token!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    private int calculateSeatIndex(int row, int column) {

        return (row - 1) * cinema.getColumns() + (column - 1);
    }
}

