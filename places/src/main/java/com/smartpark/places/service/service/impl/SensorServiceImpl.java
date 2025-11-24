package com.smartpark.places.service.   service.impl;

import com.smartpark.places.domaine.dto.SensorDto;
import com.smartpark.places.domaine.entity.Sensor;
import com.smartpark.places.exception.EntityNotFoundException;
import com.smartpark.places.mapper.SensorMapper;
import com.smartpark.places.repository.SensorRepository;
import com.smartpark.places.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository repository;
    private final SensorMapper mapper;

    @Override
    public List<SensorDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public SensorDto getById(Long id) {
        Sensor sensor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sensor not found with id " + id));
        return mapper.toDto(sensor);
    }

    @Override
    public SensorDto create(SensorDto dto) {
        Sensor entity = mapper.toEntity(dto);
        Sensor saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public SensorDto update(Long id, SensorDto dto) {
        Sensor existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sensor not found with id " + id));

        existing.setReference(dto.getReference());
        existing.setType(dto.getType());
        existing.setActive(dto.getActive());
        existing.setParkingSpot(mapper.mapSpot(dto.getParkingSpotId()));

        Sensor saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Sensor not found with id " + id);
        }
        repository.deleteById(id);
    }
}
