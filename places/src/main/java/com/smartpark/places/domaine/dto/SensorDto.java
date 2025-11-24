package com.smartpark.places.domaine.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDto {

    private Long id;

    @NotBlank
    private String reference;

    private String type;

    @NotNull
    private Boolean active;

    @NotNull
    private Long parkingSpotId;   // ⚠ ce nom DOIT être "parkingSpotId"
}
