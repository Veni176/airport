package com.bataklieva.airport.passenger;

import com.bataklieva.airport.passenger.dto.CreatePassengerRequest;
import com.bataklieva.airport.passenger.dto.PassengerResponse;
import com.bataklieva.airport.passenger.dto.UpdatePhoneNumberRequest;
import com.bataklieva.airport.passenger.exception.DuplicatePassengerException;
import com.bataklieva.airport.passenger.exception.PassengerNotFoundException;
import com.bataklieva.airport.plane.exception.DuplicatePlaneException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    @Transactional
    public PassengerResponse createPassenger(CreatePassengerRequest request) {
        String email = request.email().trim().toLowerCase();
        if (passengerRepository.existsByEmail(email)) {
            throw new DuplicatePassengerException(email);
        }
        Passenger passenger = Passenger.builder()
                .firstName(request.firstName().trim())
                .lastName(request.lastName().trim())
                .phoneNumber(request.phoneNumber())
                .email(email)
                .active(true)
                .build();

        Passenger saved = passengerRepository.save(passenger);
        log.info("Created passenger with id {}", passenger.getId());
        return PassengerResponse.from(saved);
    }

    @Transactional
    public PassengerResponse updatePhoneNumber(Long id, UpdatePhoneNumberRequest request) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new PassengerNotFoundException(id));
        passenger.setPhoneNumber(request.phoneNumber().trim());
        log.info("Updated phone number of passenger with id {}", id);
        return PassengerResponse.from(passenger);
    }

    @Transactional
    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new PassengerNotFoundException(id));
        passengerRepository.delete(passenger);
        log.info("Deleted passenger with id {}", id);
    }

    public List<PassengerResponse> getAllPassengers() {
        return passengerRepository.findAll().stream().map(PassengerResponse::from).toList();
    }

    public PassengerResponse getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new PassengerNotFoundException(id));
        return PassengerResponse.from(passenger);
    }

    public PassengerResponse getPassengerByEmail(String email) {
        Passenger passenger = passengerRepository.findByEmail(email.trim().toLowerCase())
                .orElseThrow(() -> new PassengerNotFoundException(email.trim().toLowerCase()));
        return PassengerResponse.from(passenger);
    }
}
