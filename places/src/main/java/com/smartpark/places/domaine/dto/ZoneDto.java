package com.smartpark.places.domaine.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneDto {

    private Long id;

    @NotBlank(message = "Zone name is required")
    private String name;

    @NotNull(message = "Hourly rate is required")
    private Double hourlyRate;
}
