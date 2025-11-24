package com.smartpark.places.service;

import com.smartpark.places.domaine.dto.SensorDto;

import java.util.List;

public interface SensorService {
    List<SensorDto> getAll();
    SensorDto getById(Long id);
    SensorDto create(SensorDto dto);
    SensorDto update(Long id, SensorDto dto);
    void delete(Long id);
}
