package com.bataklieva.airport.location.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long id) {
        super("Location with ID: " + id + " was not found!");
    }
}
