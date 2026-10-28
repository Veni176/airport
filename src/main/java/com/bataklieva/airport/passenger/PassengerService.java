package com.bataklieva.airport.passenger;

import com.bataklieva.airport.passenger.exception.PassengerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public void deletePassenger(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new RuntimeException("Passenger with ID: " + id + " does not exist!");
        } else {
            passengerRepository.deleteById(id);
        }
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerByEmail(String email) {
        return passengerRepository.findByEmail(email).orElseThrow(() -> new PassengerNotFoundException(email));
    }
}
