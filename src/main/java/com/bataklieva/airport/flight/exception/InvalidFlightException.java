package com.bataklieva.airport.flight.exception;

public class InvalidFlightException extends RuntimeException {
    public InvalidFlightException(String message) {
        super(message);
    }
}
