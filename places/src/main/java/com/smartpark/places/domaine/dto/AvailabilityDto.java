package com.smartpark.places.domaine.dto;

import com.smartpark.places.domaine.entity.Availability;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailabilityDto {

    private Long id;

    @NotNull
    private Long parkingSpotId;   // ⚠ nom EXACT

    @NotNull
    private Availability.Status status;

    private LocalDateTime timestamp;
}
