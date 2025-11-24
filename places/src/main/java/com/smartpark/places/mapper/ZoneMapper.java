package com.smartpark.places.mapper;

import com.smartpark.places.domaine.dto.ZoneDto;
import com.smartpark.places.domaine.entity.Zone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
    ZoneDto toDto(Zone entity);
    Zone toEntity(ZoneDto dto);
}

