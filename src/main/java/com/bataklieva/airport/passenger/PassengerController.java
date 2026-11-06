package com.bataklieva.airport.passenger;

import com.bataklieva.airport.passenger.dto.CreatePassengerRequest;
import com.bataklieva.airport.passenger.dto.PassengerResponse;
import com.bataklieva.airport.passenger.dto.UpdatePhoneNumberRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerResponse createPassenger(@Valid @RequestBody CreatePassengerRequest request) {
        return passengerService.createPassenger(request);
    }

    @PatchMapping("/{id}/phone")
    public PassengerResponse updatePhoneNumber(@PathVariable Long id ,@Valid @RequestBody UpdatePhoneNumberRequest request) {
        return passengerService.updatePhoneNumber(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }

    @GetMapping
    public List<PassengerResponse> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public PassengerResponse getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @GetMapping(params = "email")
    public PassengerResponse getPassengerByEmail(@RequestParam String email) {
        return passengerService.getPassengerByEmail(email);
    }
}
