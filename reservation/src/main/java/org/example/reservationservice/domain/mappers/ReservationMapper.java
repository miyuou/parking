package org.example.reservationservice.domain.mappers;

import org.example.reservationservice.domain.dto.reservation.ReservationCreateUpdateDTO;
import org.example.reservationservice.domain.dto.reservation.ReservationReadDTO;
import org.example.reservationservice.domain.entities.Reservation;
import org.example.reservationservice.domain.entities.User;
import org.example.reservationservice.domain.entities.Vehicule;
import org.example.reservationservice.domain.enums.ReservationStatus;
import org.mapstruct.*;

import java.util.Set;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ReservationMapper {

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToUser")
    @Mapping(target = "vehicle", source = "vehicleId", qualifiedByName = "mapVehicleIdToVehicule")
    @Mapping(target = "status", expression = "java(mapStatus(reservationCreateUpdateDTO.getStatus()))")
    Reservation toEntity(ReservationCreateUpdateDTO reservationCreateUpdateDTO);

    @Mapping(target = "userId", source = "user.id")
    ReservationReadDTO toReadDTO(Reservation reservation);

    Set<ReservationReadDTO> toReadDTOSet(Set<Reservation> reservations);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToUser")
    @Mapping(target = "vehicle", source = "vehicleId", qualifiedByName = "mapVehicleIdToVehicule")
    @Mapping(target = "status", expression = "java(mapStatus(reservationCreateUpdateDTO.getStatus()))")
    Reservation partialUpdate(ReservationCreateUpdateDTO reservationCreateUpdateDTO, @MappingTarget Reservation reservation);

    @Named("mapUserIdToUser")
    default User mapUserIdToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        return User.builder().id(userId).build();
    }

    @Named("mapVehicleIdToVehicule")
    default Vehicule mapVehicleIdToVehicule(Long vehicleId) {
        if (vehicleId == null) {
            return null;
        }
        return Vehicule.builder().id(vehicleId).build();
    }

    default ReservationStatus mapStatus(ReservationStatus status) {
        return status != null ? status : ReservationStatus.PENDING;
    }
}