package org.example.reservationservice.domain.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.reservationservice.domain.enums.ReservationStatus;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationReadDTO {
    private Long id;
    private String reservationId;
    private Long userId;
    private String email;
    private Long vehicleId;
    private String licensePlate;
    private String spotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}