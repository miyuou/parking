package org.example.reservationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.reservationservice.domain.dto.vehicule.VehiculeCreateUpdateDTO;
import org.example.reservationservice.domain.dto.vehicule.VehiculeReadDTO;
import org.example.reservationservice.domain.entities.Vehicule;
import org.example.reservationservice.domain.mappers.VehiculeMapper;
import org.example.reservationservice.domain.services.VehiculeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    @PostMapping
    public ResponseEntity<VehiculeReadDTO> createVehicle(
            @Valid @RequestBody VehiculeCreateUpdateDTO dto) {

        Vehicule vehicule = vehiculeMapper.toEntity(dto);
        Vehicule created = vehiculeService.createVehicule(vehicule);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeMapper.toReadDTO(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeReadDTO> getVehicle(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id)
                .map(vehiculeMapper::toReadDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VehiculeReadDTO>> getAllVehicles() {
        List<VehiculeReadDTO> vehicles = vehiculeService.getAllVehicules()
                .stream()
                .map(vehiculeMapper::toReadDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculeReadDTO> updateVehicle(
            @PathVariable Long id,
            @Valid @RequestBody VehiculeCreateUpdateDTO dto) {

        Vehicule existingVehicule = vehiculeService.getVehiculeById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        // Update only the allowed fields
        existingVehicule.setMake(dto.getMake());
        existingVehicule.setModel(dto.getModel());

        Vehicule updated = vehiculeService.updateVehicule(id, existingVehicule);
        return ResponseEntity.ok(vehiculeMapper.toReadDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
}