package com.smartpark.places.domaine.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpotDto {

    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private String type;

    @NotNull
    private Boolean active;

    @NotNull
    private Long zoneId;   // ⚠ ce nom DOIT être exactement "zoneId"
}
