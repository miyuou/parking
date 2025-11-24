package com.smartpark.places.service.service.impl;

import com.smartpark.places.domaine.dto.AvailabilityDto;
import com.smartpark.places.domaine.entity.Availability;
import com.smartpark.places.domaine.entity.ParkingSpot;
import com.smartpark.places.exception.EntityNotFoundException;
import com.smartpark.places.mapper.AvailabilityMapper;
import com.smartpark.places.repository.AvailabilityRepository;
import com.smartpark.places.repository.ParkingSpotRepository;
import com.smartpark.places.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final ParkingSpotRepository spotRepository;
    private final AvailabilityMapper mapper;

    @Override
    public List<AvailabilityDto> getAll() {
        return availabilityRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<AvailabilityDto> getBySpot(Long spotId) {
        return availabilityRepository.findByParkingSpotId(spotId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public AvailabilityDto create(AvailabilityDto dto) {

        ParkingSpot spot = spotRepository.findById(dto.getParkingSpotId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Parking spot not found with id " + dto.getParkingSpotId()
                ));

        Availability entity = mapper.toEntity(dto);
        entity.setTimestamp(LocalDateTime.now());
        entity.setParkingSpot(spot);

        Availability saved = availabilityRepository.save(entity);

        return mapper.toDto(saved);
    }
}

