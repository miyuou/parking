package com.smartpark.places.service;

import com.smartpark.places.domaine.dto.ParkingSpotDto;

import java.util.List;

public interface ParkingSpotService {
    List<ParkingSpotDto> getAll();
    List<ParkingSpotDto> getByZone(Long zoneId);
    ParkingSpotDto getById(Long id);
    ParkingSpotDto create(ParkingSpotDto dto);
    ParkingSpotDto update(Long id, ParkingSpotDto dto);
    void delete(Long id);
}
