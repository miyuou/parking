package org.example.reservationservice.domain.services.impl;



import org.example.reservationservice.domain.entities.User;
import org.example.reservationservice.domain.entities.Vehicule;
import org.example.reservationservice.domain.repositories.UserRepository;
import org.example.reservationservice.domain.repositories.VehiculeRepository;
import org.example.reservationservice.domain.services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final UserRepository userRepository;

    @Autowired
    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository, UserRepository userRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vehicule createVehicule(Vehicule vehicule) {
        if (vehiculeRepository.existsByLicensePlate(vehicule.getLicensePlate())) {
            throw new RuntimeException("Vehicle with license plate " + vehicule.getLicensePlate() + " already exists");
        }

        // Verify user exists
        User user = userRepository.findById(vehicule.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + vehicule.getUser().getId()));

        vehicule.setUser(user);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Long id, Vehicule vehicule) {
        Vehicule existingVehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        existingVehicule.setMake(vehicule.getMake());
        existingVehicule.setModel(vehicule.getModel());

        return vehiculeRepository.save(existingVehicule);
    }

    @Override
    public void deleteVehicule(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicule> getVehiculeById(Long id) {
        return vehiculeRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicule> getVehiculeByLicensePlate(String licensePlate) {
        return vehiculeRepository.findByLicensePlate(licensePlate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> getVehiculesByUser(User user) {
        return vehiculeRepository.findByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByLicensePlate(String licensePlate) {
        return vehiculeRepository.existsByLicensePlate(licensePlate);
    }
}
