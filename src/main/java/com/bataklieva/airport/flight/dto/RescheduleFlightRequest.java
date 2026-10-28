package com.bataklieva.airport.flight.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record RescheduleFlightRequest(
        @NotNull Instant newDepartureTime,
        @NotNull Instant newArrivalTime
) {
}
