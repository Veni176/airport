package com.bataklieva.airport.plane.dto;

import com.bataklieva.airport.plane.Plane;

public record PlaneResponse(
        Long id,
        String name,
        String registrationNumber,
        Short seatCapacity,
        Boolean taken
) {
    public static PlaneResponse from(Plane plane) {
        return new PlaneResponse(
                plane.getId(),
                plane.getName(),
                plane.getRegistrationNumber(),
                plane.getSeatCapacity(),
                plane.isTaken()
        );
    }
}
