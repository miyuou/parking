package org.example.reservationservice.domain.services;



import org.example.reservationservice.domain.entities.User;
import org.example.reservationservice.domain.entities.Vehicule;
import java.util.List;
import java.util.Optional;

public interface VehiculeService {
    Vehicule createVehicule(Vehicule vehicule);
    Vehicule updateVehicule(Long id, Vehicule vehicule);
    void deleteVehicule(Long id);
    Optional<Vehicule> getVehiculeById(Long id);
    Optional<Vehicule> getVehiculeByLicensePlate(String licensePlate);
    List<Vehicule> getVehiculesByUser(User user);
    List<Vehicule> getAllVehicules();
    boolean existsByLicensePlate(String licensePlate);
}
