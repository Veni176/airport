package com.bataklieva.airport.passenger.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdatePhoneNumberRequest(
        @NotBlank @Pattern(regexp = "^\\+?[0-9]{7,15}") String phoneNumber
) {
}
