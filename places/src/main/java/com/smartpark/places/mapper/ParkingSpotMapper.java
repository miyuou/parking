package com.smartpark.places.mapper;

import com.smartpark.places.domaine.dto.ParkingSpotDto;
import com.smartpark.places.domaine.entity.ParkingSpot;
import com.smartpark.places.domaine.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkingSpotMapper {

    @Mapping(
            target = "zoneId",
            expression = "java( entity.getZone() != null ? entity.getZone().getId() : null )"
    )
    ParkingSpotDto toDto(ParkingSpot entity);

    @Mapping(
            target = "zone",
            expression = "java( mapZone(dto.getZoneId()) )"
    )
    ParkingSpot toEntity(ParkingSpotDto dto);

    default Zone mapZone(Long zoneId) {
        if (zoneId == null) return null;
        Zone z = new Zone();
        z.setId(zoneId);
        return z;
    }
}
