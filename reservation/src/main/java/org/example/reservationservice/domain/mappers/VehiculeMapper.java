package org.example.reservationservice.domain.mappers;

import org.example.reservationservice.domain.dto.vehicule.VehiculeCreateUpdateDTO;
import org.example.reservationservice.domain.dto.vehicule.VehiculeReadDTO;
import org.example.reservationservice.domain.entities.User;
import org.example.reservationservice.domain.entities.Vehicule;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface VehiculeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToUser")
    @Mapping(target = "reservations", ignore = true)
    Vehicule toEntity(VehiculeCreateUpdateDTO dto);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    VehiculeReadDTO toReadDTO(Vehicule vehicule);

    @Named("mapUserIdToUser")
    default User mapUserIdToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        return User.builder().id(userId).build();
    }
}