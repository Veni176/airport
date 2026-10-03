package com.bataklieva.airport.plane;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "seat_number")
    private short seatNumber;

    @Column(name = "status", nullable = false)
    private boolean isTaken;
}
