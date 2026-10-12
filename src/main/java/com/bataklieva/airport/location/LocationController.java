package com.bataklieva.airport.location;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @DeleteMapping("/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return "Location with ID: " + id + " was deleted!";
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{country}")
    public Optional<Location> getLocationByCountry(@PathVariable String country) {
        return locationService.getLocationByCountry(country);
    }

    @GetMapping("/{city}")
    public Optional<Location> getLocationByCity(@PathVariable String city) {
        return locationService.getLocationByCity(city);
    }
}
