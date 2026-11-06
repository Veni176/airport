package com.bataklieva.airport.plane.exception;

public class DuplicatePlaneException extends RuntimeException {
    public DuplicatePlaneException(String registrationNumber) {
        super("Plane with registration number: " + registrationNumber + " already exists!");
    }
}
