package org.example.reservationservice.domain.dto.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.reservationservice.domain.enums.ReservationStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateUpdateDTO {
    @NotBlank(message = "Reservation ID is mandatory")
    @Size(max = 50, message = "Reservation ID cannot exceed 50 characters")
    private String reservationId;

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotNull(message = "Vehicle ID is mandatory")
    private Long vehicleId;

    @NotBlank(message = "Spot ID is mandatory")
    @Size(max = 50, message = "Spot ID cannot exceed 50 characters")
    private String spotId;

    @NotNull(message = "Start time is mandatory")
    private LocalDateTime startTime;

    @NotNull(message = "End time is mandatory")
    private LocalDateTime endTime;

    private ReservationStatus status;
}