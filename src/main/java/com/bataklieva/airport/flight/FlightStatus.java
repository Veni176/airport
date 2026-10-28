package com.bataklieva.airport.flight;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FlightStatus {
    SCHEDULED("Scheduled"),
    DELAYED("Delayed"),
    BOARDING("Boarding"),
    DEPARTED("Departed"),
    ARRIVED("Arrived"),
    CANCELLED("Cancelled");

    private final String displayName;
}
