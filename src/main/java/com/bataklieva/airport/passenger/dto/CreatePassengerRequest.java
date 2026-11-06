package com.bataklieva.airport.passenger.dto;

import jakarta.validation.constraints.*;

public record CreatePassengerRequest(
        @NotBlank @Size(max = 50) String firstName,
        @NotBlank @Size(max = 100) String lastName,
        @Pattern(regexp = "^\\+?[0-9]{7,15}$") @Size(max = 30) String phoneNumber,
        @NotBlank @Email @Size(max = 100) String email
) {
}
