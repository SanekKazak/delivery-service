package org.spring.usersservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.spring.usersservice.dto.request.UserCreateDto;
import org.spring.usersservice.dto.response.UserInfoDto;
import org.spring.usersservice.entity.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper{
    @Mapping(target = "login", source = "login")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    UserEntity fromRegisterToEntity(UserCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserInfoDto fromEntityToInfoDto(UserEntity entity);

    List<UserInfoDto> fromEntitiesToInfoDtos(List<UserEntity> entities);
}
