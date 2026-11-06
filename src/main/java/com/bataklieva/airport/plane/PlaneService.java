package com.bataklieva.airport.plane;

import com.bataklieva.airport.plane.dto.CreatePlaneRequest;
import com.bataklieva.airport.plane.dto.PlaneResponse;
import com.bataklieva.airport.plane.exception.DuplicatePlaneException;
import com.bataklieva.airport.plane.exception.PlaneNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;

    @Transactional
    public PlaneResponse createPlane(CreatePlaneRequest request) {
        String registrationNumber = request.registrationNumber().trim().toUpperCase();
        if (planeRepository.existsByRegistrationNumber(registrationNumber)) {
            throw new DuplicatePlaneException(registrationNumber);
        }
        Plane plane = Plane.builder()
                .name(request.name())
                .registrationNumber(registrationNumber)
                .seatCapacity(request.seatCapacity())
                .build();
        Plane saved = planeRepository.save(plane);
        log.info("Created plane with id {}", saved.getId());
        return PlaneResponse.from(saved);
    }

    @Transactional
    public void deletePlane(Long id) {
        Plane plane = planeRepository.findById(id).orElseThrow(() -> new PlaneNotFoundException(id));
        planeRepository.delete(plane);
        log.info("Deleted plane {}", id);
    }

    public PlaneResponse getPlaneById(Long id) {
        Plane plane = planeRepository.findById(id).orElseThrow(() -> new PlaneNotFoundException(id));
        return PlaneResponse.from(plane);
    }

    public List<PlaneResponse> getAllPlanes() {
        return planeRepository.findAll().stream().map(PlaneResponse::from).toList();
    }
}
