package com.bataklieva.airport.flight.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record CreateFlightRequest(
        @NotBlank String flightNumber,
        @NotNull Long startingLocationId,
        @NotNull Long destinationLocationId,
        @NotNull Instant departureTime,
        @NotNull Instant arrivalTime,
        @NotNull Long planeId
) {

}
