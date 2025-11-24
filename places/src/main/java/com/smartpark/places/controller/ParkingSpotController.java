package com.smartpark.places.controller;

import com.smartpark.places.domaine.dto.ParkingSpotDto;
import com.smartpark.places.service.ParkingSpotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService service;

    @GetMapping
    public List<ParkingSpotDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ParkingSpotDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/by-zone/{zoneId}")
    public List<ParkingSpotDto> getByZone(@PathVariable Long zoneId) {
        return service.getByZone(zoneId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingSpotDto create(@Valid @RequestBody ParkingSpotDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ParkingSpotDto update(@PathVariable Long id,
                                 @Valid @RequestBody ParkingSpotDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
