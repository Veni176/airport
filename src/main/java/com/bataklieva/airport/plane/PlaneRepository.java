package com.bataklieva.airport.plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long> {
    boolean existsByRegistrationNumber(String registrationNumber);
}
