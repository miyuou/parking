package com.smartpark.places.service;
import com.smartpark.places.domaine.dto.ZoneDto;
import java.util.List;

public interface ZoneService {

    List<ZoneDto> getAll();

    ZoneDto getById(Long id);

    ZoneDto create(ZoneDto dto);

    ZoneDto update(Long id, ZoneDto dto);

    void delete(Long id);
}

