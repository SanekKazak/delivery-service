package org.spring.authservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.spring.authservice.dto.input.auth.UserCredentialsDto;
import org.spring.authservice.dto.input.user.UserCreateDto;
import org.spring.authservice.dto.output.UserDetailsDto;
import org.spring.authservice.entity.UserDetailsEntity;
import org.spring.authservice.entity.UserRoleEntity;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDetailsMapper {
    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "referenceId", ignore = true)
    UserDetailsEntity fromDtoToEntity(UserCredentialsDto dto);

    @Mapping(target = "id", source = "referenceId")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "roles", source = "roles")
    UserDetailsDto fromEntityToDto(UserDetailsEntity dto);

    @Mapping(target = "login", source = "login")
    UserCreateDto fromCredentialsToCreateDto(UserCredentialsDto dto);


    default String fromRoleToString(UserRoleEntity role) {
        return role.getName();
    }
}
