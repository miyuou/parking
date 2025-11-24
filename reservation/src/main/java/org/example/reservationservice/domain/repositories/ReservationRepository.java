package org.example.reservationservice.domain.repositories;



import org.example.reservationservice.domain.entities.Reservation;
import org.example.reservationservice.domain.entities.User;
import org.example.reservationservice.domain.entities.Vehicule;
import org.example.reservationservice.domain.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByReservationId(String reservationId);
    List<Reservation> findByUser(User user);
    List<Reservation> findByVehicle(Vehicule vehicle);
    List<Reservation> findByStatus(ReservationStatus status);
    List<Reservation> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByReservationId(String reservationId);
}
