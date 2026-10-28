package com.bataklieva.airport.flight;

import com.bataklieva.airport.flight.dto.CreateFlightRequest;
import com.bataklieva.airport.flight.dto.FlightResponse;
import com.bataklieva.airport.flight.dto.RescheduleFlightRequest;
import com.bataklieva.airport.flight.exception.DuplicateFlightException;
import com.bataklieva.airport.flight.exception.FlightNotFoundException;
import com.bataklieva.airport.flight.exception.InvalidFlightException;
import com.bataklieva.airport.flight.exception.InvalidFlightOperationException;
import com.bataklieva.airport.location.Location;
import com.bataklieva.airport.location.exception.LocationNotFoundException;
import com.bataklieva.airport.location.LocationRepository;
import com.bataklieva.airport.plane.Plane;
import com.bataklieva.airport.plane.exception.PlaneNotFoundException;
import com.bataklieva.airport.plane.PlaneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final LocationRepository locationRepository;
    private final PlaneRepository planeRepository;

    @Transactional
    public FlightResponse createFlight(CreateFlightRequest request) {
        String flightNumber = request.flightNumber().trim().toUpperCase();

        if (request.startingLocationId().equals(request.destinationLocationId())) {
            throw new InvalidFlightException("Starting location and destination location must differ!");
        }
        if (flightRepository.existsByNumberAndDepartureTime(flightNumber, request.departureTime())) {
            throw new DuplicateFlightException(flightNumber, request.departureTime());
        }

        Location startingLocation = locationRepository.findById(request.startingLocationId())
                .orElseThrow(() -> new LocationNotFoundException(request.startingLocationId()));
        Location destinationLocation = locationRepository.findById(request.destinationLocationId())
                .orElseThrow(() -> new LocationNotFoundException(request.destinationLocationId()));
        Plane plane =planeRepository.findById(request.planeId())
                .orElseThrow(() -> new PlaneNotFoundException(request.planeId()));

        Flight flight = Flight.builder()
                .flightNumber(flightNumber)
                .startingLocation(startingLocation)
                .destinationLocation(destinationLocation)
                .departureTime(request.departureTime())
                .arrivalTime(request.arrivalTime())
                .flightStatus(FlightStatus.SCHEDULED)
                .plane(plane)
                .build();

        Flight saved = flightRepository.save(flight);
        log.info("Created flight {} (id {})", saved.getFlightNumber(), saved.getId());
        return FlightResponse.from(saved);
    }

    @Transactional
    public FlightResponse rescheduleFlight(Long id, RescheduleFlightRequest request) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));

        if (flight.getFlightStatus() != FlightStatus.SCHEDULED && flight.getFlightStatus() != FlightStatus.DELAYED) {
            throw new InvalidFlightException("Only scheduled or delayed flights can be rescheduled!");
        }
        if (!request.newArrivalTime().isAfter(request.newDepartureTime())) {
            throw new InvalidFlightException("Arrival time must be after departure time!");
        }

        flight.setArrivalTime(request.newArrivalTime());
        flight.setDepartureTime(request.newDepartureTime());
        log.info("Rescheduled flight {}", id);
        return FlightResponse.from(flight);
    }

    @Transactional
    public FlightResponse cancelFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));

        if (flight.getFlightStatus() == FlightStatus.CANCELLED || flight.getFlightStatus() == FlightStatus.ARRIVED) {
            throw new InvalidFlightOperationException("Flight cannot be cancelled!");
        }

        flight.setFlightStatus(FlightStatus.CANCELLED);
        log.info("Cancelled flight {}", id);
        return FlightResponse.from(flight);
    }

    public FlightResponse getFlightById(Long id) {
        return FlightResponse.from(flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(FlightResponse::from).toList();
    }

    public List<FlightResponse> getFlightsByStatus(FlightStatus flightStatus) {
        return flightRepository.findByStatus(flightStatus).stream()
                .map(FlightResponse::from).toList();
    }
}
