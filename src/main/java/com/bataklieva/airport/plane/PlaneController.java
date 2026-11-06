package com.bataklieva.airport.plane;

import com.bataklieva.airport.plane.dto.CreatePlaneRequest;
import com.bataklieva.airport.plane.dto.PlaneResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaneResponse createPlane(@Valid @RequestBody CreatePlaneRequest request) {
        return planeService.createPlane(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlane(@PathVariable Long id) {
        planeService.deletePlane(id);
    }

    @GetMapping
    public List<PlaneResponse> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @GetMapping("/{id}")
    public PlaneResponse getPlaneById(@PathVariable Long id) {
        return planeService.getPlaneById(id);
    }
}
