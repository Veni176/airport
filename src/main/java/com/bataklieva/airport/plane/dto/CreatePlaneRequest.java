package com.bataklieva.airport.plane.dto;

import jakarta.validation.constraints.*;

public record CreatePlaneRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 20) String registrationNumber,
        @NotNull @Min(1) @Max(900) Short seatCapacity
) {
}
