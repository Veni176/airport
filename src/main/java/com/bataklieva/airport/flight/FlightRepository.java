package com.bataklieva.airport.flight;

import com.bataklieva.airport.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByStartingLocationId(Long startingLocationId);

    List<Flight> findByStartingLocationAndDestinationLocation(Location startingLocation, Location destinationLocation);

    boolean existsByNumberAndDepartureTime(String flightNumber, Instant departureTime);

    List<Flight> findByStatus(FlightStatus status);

}
