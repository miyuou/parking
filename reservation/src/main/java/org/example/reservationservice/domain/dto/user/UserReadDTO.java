package org.example.reservationservice.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Instant createdAt;
    private Instant updatedAt;
}