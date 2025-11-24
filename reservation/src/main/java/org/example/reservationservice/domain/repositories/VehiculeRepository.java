package org.example.reservationservice.domain.repositories;



import org.example.reservationservice.domain.entities.User;
import org.example.reservationservice.domain.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    Optional<Vehicule> findByLicensePlate(String licensePlate);
    List<Vehicule> findByUser(User user);
    boolean existsByLicensePlate(String licensePlate);
}
