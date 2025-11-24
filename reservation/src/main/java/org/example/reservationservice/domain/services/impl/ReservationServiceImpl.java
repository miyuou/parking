package org.example.reservationservice.domain.services.impl;

import org.example.reservationservice.domain.entities.Reservation;
import org.example.reservationservice.domain.entities.User;
import org.example.reservationservice.domain.entities.Vehicule;
import org.example.reservationservice.domain.enums.ReservationStatus;
import org.example.reservationservice.domain.repositories.ReservationRepository;
import org.example.reservationservice.domain.repositories.UserRepository;
import org.example.reservationservice.domain.repositories.VehiculeRepository;
import org.example.reservationservice.domain.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final VehiculeRepository vehiculeRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserRepository userRepository,
                                  VehiculeRepository vehiculeRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        if (reservationRepository.existsByReservationId(reservation.getReservationId())) {
            throw new RuntimeException("Reservation with ID " + reservation.getReservationId() + " already exists");
        }

        // Verify user exists
        User user = userRepository.findById(reservation.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + reservation.getUser().getId()));

        // Verify vehicle exists
        Vehicule vehicle = vehiculeRepository.findById(reservation.getVehicle().getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + reservation.getVehicle().getId()));

        reservation.setUser(user);
        reservation.setVehicle(vehicle);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));

        existingReservation.setSpotId(reservation.getSpotId());
        existingReservation.setStartTime(reservation.getStartTime());
        existingReservation.setEndTime(reservation.getEndTime());
        existingReservation.setStatus(reservation.getStatus());

        return reservationRepository.save(existingReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Reservation not found with id: " + id);
        }
        reservationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> getReservationByReservationId(String reservationId) {
        return reservationRepository.findByReservationId(reservationId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByUser(User user) {
        return reservationRepository.findByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByVehicle(Vehicule vehicle) {
        return reservationRepository.findByVehicle(vehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByStatus(ReservationStatus status) {
        return reservationRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return reservationRepository.findByStartTimeBetween(start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByReservationId(String reservationId) {
        return reservationRepository.existsByReservationId(reservationId);
    }
}