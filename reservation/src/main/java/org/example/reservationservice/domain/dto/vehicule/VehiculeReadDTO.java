package org.example.reservationservice.domain.dto.vehicule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeReadDTO {
    private Long id;
    private Long userId;
    private String email;
    private String licensePlate;
    private String make;
    private String model;
    private Instant createdAt;
}