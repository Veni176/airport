package com.bataklieva.airport.plane;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {

    private final PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public Plane createPlane(Plane plane) {
        return planeRepository.save(plane);
    }

    public void deletePlane(Long id) {
        if (!planeRepository.existsById(id)) {
            throw new RuntimeException("Plane with ID " + id + " is not found!");
        } else {
            planeRepository.deleteById(id);
        }
    }

    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    public Optional<Plane> getPlaneById(Long id) {
        if (!planeRepository.existsById(id)) {
            throw new RuntimeException("Plane with " + id + " ID is not found!");
        }
        return planeRepository.findById(id);
    }
}
