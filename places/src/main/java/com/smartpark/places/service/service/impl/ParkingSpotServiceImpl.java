package com.smartpark.places.service.service.impl;

import com.smartpark.places.domaine.dto.ParkingSpotDto;
import com.smartpark.places.domaine.entity.ParkingSpot;
import com.smartpark.places.exception.EntityNotFoundException;
import com.smartpark.places.mapper.ParkingSpotMapper;
import com.smartpark.places.repository.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.smartpark.places.service.ParkingSpotService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository repository;
    private final ParkingSpotMapper mapper;

    @Override
    public List<ParkingSpotDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<ParkingSpotDto> getByZone(Long zoneId) {
        return repository.findByZoneId(zoneId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ParkingSpotDto getById(Long id) {
        ParkingSpot spot = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parking spot not found with id " + id));
        return mapper.toDto(spot);
    }

    @Override
    public ParkingSpotDto create(ParkingSpotDto dto) {
        ParkingSpot entity = mapper.toEntity(dto);
        ParkingSpot saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public ParkingSpotDto update(Long id, ParkingSpotDto dto) {
        ParkingSpot existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parking spot not found with id " + id));

        existing.setCode(dto.getCode());
        existing.setType(dto.getType());
        existing.setActive(dto.getActive());

        // zoneId déjà géré par mapper via dto.getZoneId()
        existing.setZone(mapper.mapZone(dto.getZoneId()));

        ParkingSpot saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Parking spot not found with id " + id);
        }
        repository.deleteById(id);
    }
}
