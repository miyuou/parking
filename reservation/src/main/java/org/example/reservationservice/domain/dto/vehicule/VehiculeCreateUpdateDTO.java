package org.example.reservationservice.domain.dto.vehicule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeCreateUpdateDTO {
    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotBlank(message = "License plate is mandatory")
    @Size(max = 20, message = "License plate cannot exceed 20 characters")
    private String licensePlate;

    @Size(max = 50, message = "Make cannot exceed 50 characters")
    private String make;

    @Size(max = 50, message = "Model cannot exceed 50 characters")
    private String model;
}