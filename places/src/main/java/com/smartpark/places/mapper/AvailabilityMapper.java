package com.smartpark.places.mapper;

import com.smartpark.places.domaine.dto.AvailabilityDto;
import com.smartpark.places.domaine.entity.Availability;
import com.smartpark.places.domaine.entity.ParkingSpot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AvailabilityMapper {

    @Mapping(
            target = "parkingSpotId",
            expression = "java( entity.getParkingSpot() != null ? entity.getParkingSpot().getId() : null )"
    )
    AvailabilityDto toDto(Availability entity);

    @Mapping(
            target = "parkingSpot",
            expression = "java( mapSpot(dto.getParkingSpotId()) )"
    )
    Availability toEntity(AvailabilityDto dto);

    default ParkingSpot mapSpot(Long spotId) {
        if (spotId == null) return null;
        ParkingSpot spot = new ParkingSpot();
        spot.setId(spotId);
        return spot;
    }
}
