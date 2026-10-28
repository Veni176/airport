package com.bataklieva.airport.flight.exception;

public class InvalidFlightOperationException extends RuntimeException {
    public InvalidFlightOperationException(String message) {
        super(message);
    }
}
