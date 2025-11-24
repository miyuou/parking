package com.smartpark.places.service.service.impl;


import com.smartpark.places.domaine.dto.ZoneDto;
import com.smartpark.places.domaine.entity.Zone;
import com.smartpark.places.exception.EntityNotFoundException;
import com.smartpark.places.mapper.ZoneMapper;
import org.springframework.stereotype.Service;
import com.smartpark.places.repository.ZoneRepository;
import com.smartpark.places.service.ZoneService;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    public ZoneServiceImpl(ZoneRepository zoneRepository, ZoneMapper zoneMapper) {
        this.zoneRepository = zoneRepository;
        this.zoneMapper = zoneMapper;
    }

    @Override
    public List<ZoneDto> getAll() {
        return zoneRepository.findAll()
                .stream()
                .map(zoneMapper::toDto)
                .toList();
    }

    @Override
    public ZoneDto getById(Long id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zone not found with id " + id));
        return zoneMapper.toDto(zone);
    }

    @Override
    public ZoneDto create(ZoneDto dto) {
        Zone zone = zoneMapper.toEntity(dto);
        Zone saved = zoneRepository.save(zone);
        return zoneMapper.toDto(saved);
    }

    @Override
    public ZoneDto update(Long id, ZoneDto dto) {
        Zone existing = zoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zone not found with id " + id));

        existing.setName(dto.getName());
        existing.setHourlyRate(dto.getHourlyRate());

        Zone saved = zoneRepository.save(existing);
        return zoneMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!zoneRepository.existsById(id)) {
            throw new EntityNotFoundException("Zone not found with id " + id);
        }
        zoneRepository.deleteById(id);
    }
}

