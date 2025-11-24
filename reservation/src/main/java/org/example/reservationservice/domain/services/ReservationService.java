package org.example.reservationservice.domain.services;


import org.example.reservationservice.domain.entities.Reservation;
import org.example.reservationservice.domain.entities.User;
import org.example.reservationservice.domain.entities.Vehicule;
import org.example.reservationservice.domain.enums.ReservationStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
    Optional<Reservation> getReservationById(Long id);
    Optional<Reservation> getReservationByReservationId(String reservationId);
    List<Reservation> getReservationsByUser(User user);
    List<Reservation> getReservationsByVehicle(Vehicule vehicle);
    List<Reservation> getReservationsByStatus(ReservationStatus status);
    List<Reservation> getReservationsByTimeRange(LocalDateTime start, LocalDateTime end);
    List<Reservation> getAllReservations();
    boolean existsByReservationId(String reservationId);
}
