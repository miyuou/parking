package com.smartpark.places.mapper;

import com.smartpark.places.domaine.dto.SensorDto;
import com.smartpark.places.domaine.entity.ParkingSpot;
import com.smartpark.places.domaine.entity.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    @Mapping(
            target = "parkingSpotId",
            expression = "java( entity.getParkingSpot() != null ? entity.getParkingSpot().getId() : null )"
    )
    SensorDto toDto(Sensor entity);

    @Mapping(
            target = "parkingSpot",
            expression = "java( mapSpot(dto.getParkingSpotId()) )"
    )
    Sensor toEntity(SensorDto dto);

    default ParkingSpot mapSpot(Long spotId) {
        if (spotId == null) return null;
        ParkingSpot spot = new ParkingSpot();
        spot.setId(spotId);
        return spot;
    }
}
