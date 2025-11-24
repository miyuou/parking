package org.example.reservationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.reservationservice.domain.dto.reservation.ReservationCreateUpdateDTO;
import org.example.reservationservice.domain.dto.reservation.ReservationReadDTO;
import org.example.reservationservice.domain.entities.Reservation;
import org.example.reservationservice.domain.mappers.ReservationMapper;
import org.example.reservationservice.domain.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @PostMapping
    public ResponseEntity<ReservationReadDTO> createReservation(
            @Valid @RequestBody ReservationCreateUpdateDTO dto) {

        Reservation reservation = reservationMapper.toEntity(dto);
        Reservation created = reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationMapper.toReadDTO(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationReadDTO> getReservation(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(reservationMapper::toReadDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ReservationReadDTO>> getAllReservations() {
        List<ReservationReadDTO> reservations = reservationService.getAllReservations()
                .stream()
                .map(reservationMapper::toReadDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationReadDTO> updateReservation(
            @PathVariable Long id,
            @Valid @RequestBody ReservationCreateUpdateDTO dto) {

        Reservation existingReservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));

        Reservation updatedReservation = reservationMapper.partialUpdate(dto, existingReservation);
        Reservation updated = reservationService.updateReservation(id, updatedReservation);
        return ResponseEntity.ok(reservationMapper.toReadDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}