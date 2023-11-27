package com.cinema_name.cinema_service.controller;

import com.cinema_name.cinema_service.domain.CinemaTheatre;
import com.cinema_name.cinema_service.domain.Seat;
import com.cinema_name.cinema_service.domain.Ticket;
import com.cinema_name.cinema_service.domain.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CinemaController {
    private final CinemaTheatre cinema = new CinemaTheatre(9, 9);
    private List<Seat> availableSeats = cinema.getAvailableSeats();
    private List<Ticket> purchasedTickets = new ArrayList<>();

    @GetMapping("/seats")
    public Map<String, ?> getSeats() {
        return Map.of(
                "rows", cinema.getTotalRows(),
                "columns", cinema.getTotalColumns(),
                "seats", cinema.getAvailableSeats()
        );
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();

        if (row > 9 || row < 1 || column > 9 || column < 1)
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);

        Seat selectedseat = new Seat(row, column);

        if (availableSeats.contains(selectedseat)) {
            UUID token = UUID.randomUUID();
            purchasedTickets.add(new Ticket(token, selectedseat));

            availableSeats.remove(selectedseat);
            return new ResponseEntity<>(Map.of("token", token,
                    "ticket", selectedseat), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return")
    public ResponseEntity<?> refundTicket(@RequestBody Token token) {

        Optional<Ticket> ticket = purchasedTickets.stream()
                .filter(t -> t.token().equals(token.token()))
                .findFirst();

        if (ticket.isPresent()) {
            availableSeats.add(ticket.get().seat());
            purchasedTickets.remove(ticket.get());
            return new ResponseEntity<>(Map.of("ticket", ticket.get().seat()),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> showStatistics(@RequestParam(required = false) String password) {
        if (password != null && password.equals("super_secret")) {
            int income = 0;
            for (Ticket t : purchasedTickets)
                income += t.seat().getPrice();

            return new ResponseEntity<>(Map.of("income", income,
                    "available", availableSeats.size(),
                    "purchased", purchasedTickets.size()), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.valueOf(401));
    }

}
