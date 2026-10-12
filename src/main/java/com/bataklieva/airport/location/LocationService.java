package com.bataklieva.airport.location;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new RuntimeException("Location with ID: " + id + " was deleted!");
        } else {
            locationRepository.deleteById(id);
        }
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationByCountry(String country) {
        return locationRepository.findByCountry(country);
    }
    public Optional<Location> getLocationByCity(String city) {
        return locationRepository.findByCity(city);
    }
}
