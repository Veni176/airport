package com.bataklieva.airport.passenger.exception;

public class DuplicatePassengerException extends RuntimeException {
    public DuplicatePassengerException(String email) {
        super("Passenger with email: " + email + " already exists!");
    }
}
