package com.bataklieva.airport.passenger.dto;

import com.bataklieva.airport.passenger.Passenger;

public record PassengerResponse(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        Boolean active
) {
    public static PassengerResponse from(Passenger passenger) {
        return new PassengerResponse(
                passenger.getId(),
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getPhoneNumber(),
                passenger.getEmail(),
                passenger.isActive()
        );
    }
}
