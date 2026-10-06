package com.bataklieva.airport.plane;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planes")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @PostMapping
    public Plane createPlane(@RequestBody Plane plane) {
        return planeService.createPlane(plane);
    }

    @DeleteMapping("/{id}")
    public String deletePlane(@PathVariable Long id) {
        planeService.deletePlane(id);
        return "Plane with ID: " + id + " is deleted!";
    }

    @GetMapping
    public List<Plane> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @GetMapping("/{id}")
    public Optional<Plane> getPlaneById(@PathVariable Long id) {
        return planeService.getPlaneById(id);
    }
}
