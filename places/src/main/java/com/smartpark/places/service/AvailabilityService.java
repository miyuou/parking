package com.smartpark.places.service;

import com.smartpark.places.domaine.dto.AvailabilityDto;

import java.util.List;

public interface AvailabilityService {
    List<AvailabilityDto> getAll();
    List<AvailabilityDto> getBySpot(Long spotId);
    AvailabilityDto create(AvailabilityDto dto);
}
