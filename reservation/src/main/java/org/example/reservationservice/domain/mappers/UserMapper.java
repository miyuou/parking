package org.example.reservationservice.domain.mappers;

import org.example.reservationservice.domain.dto.user.UserCreateUpdateDTO;
import org.example.reservationservice.domain.dto.user.UserReadDTO;
import org.example.reservationservice.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UserMapper {

    //@Mapping(target = "id", ignore = true)
    //@Mapping(target = "createdAt", ignore = true)
    //@Mapping(target = "updatedAt", ignore = true)
    //@Mapping(target = "vehicles", ignore = true)
    //@Mapping(target = "reservations", ignore = true)
    User toEntity(UserCreateUpdateDTO userCreateUpdateDTO);

    UserReadDTO toReadDTO(User user);
}