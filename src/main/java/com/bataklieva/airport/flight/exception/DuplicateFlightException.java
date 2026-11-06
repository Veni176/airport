package com.bataklieva.airport.flight.exception;

import java.time.Instant;

public class DuplicateFlightException extends RuntimeException {
    public DuplicateFlightException(String flightNumber, Instant departureTime) {
        super("Flight with number: " + flightNumber + " and departure time: " + departureTime +
                " already exists!");
    }
}
