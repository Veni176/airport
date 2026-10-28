package com.bataklieva.airport.flight;

import com.bataklieva.airport.flight.dto.CreateFlightRequest;
import com.bataklieva.airport.flight.dto.FlightResponse;
import com.bataklieva.airport.flight.dto.RescheduleFlightRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightResponse createFlight(@Valid @RequestBody CreateFlightRequest request) {
        return flightService.createFlight(request);
    }

    @PatchMapping("/{id}/cancel")
    public FlightResponse rescheduleFlight(@PathVariable Long id,@Valid @RequestBody RescheduleFlightRequest request) {
        return flightService.rescheduleFlight(id, request);
    }

    @PostMapping("/{id}/cancel")
    public FlightResponse cancelFlight(@PathVariable Long id) {
        return flightService.cancelFlight(id);
    }

    @GetMapping("/{id}")
    public FlightResponse getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @GetMapping
    public List<FlightResponse> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{status}")
    public List<FlightResponse> getFlightByStatus(@PathVariable FlightStatus status) {
        return flightService.getFlightsByStatus(status);
    }
}
