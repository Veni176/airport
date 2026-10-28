package com.bataklieva.airport.flight.dto;

import com.bataklieva.airport.flight.Flight;
import com.bataklieva.airport.flight.FlightStatus;

import java.time.Instant;

public record FlightResponse(
        Long id,
        String flightNumber,
        String startingCity,
        String startingAirport,
        String destinationCity,
        String destinationAirport,
        Instant departureTime,
        Instant arrivalTime,
        String planeName,
        FlightStatus flightStatus
) {
    public static FlightResponse from(Flight flight) {
        return new FlightResponse(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getStartingLocation().getCity(),
                flight.getStartingLocation().getAirportName(),
                flight.getDestinationLocation().getCity(),
                flight.getDestinationLocation().getAirportName(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getPlane().getName(),
                flight.getFlightStatus()
        );
    }
}
