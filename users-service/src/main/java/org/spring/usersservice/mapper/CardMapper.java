package org.spring.usersservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.spring.usersservice.dto.request.CardCreateDto;
import org.spring.usersservice.dto.response.CardInfoDto;
import org.spring.usersservice.entity.CardEntity;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CardMapper {
    @Mapping(target = "token", source = "token")
    @Mapping(target = "panEnd", source = "panEnd")
    @Mapping(target = "cardSupplier", source = "cardSupplier")
    @Mapping(target = "id", source = "id")
    CardInfoDto fromEntityToInfoDto(CardEntity entity);

    @Mapping(target = "token", source = "token")
    @Mapping(target = "panEnd", source = "panEnd")
    @Mapping(target = "cardSupplier", source = "cardSupplier")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    CardEntity fromCreateDtoToEntity(CardCreateDto dto);

    List<CardInfoDto> fromEntitiesToInfoDtos(List<CardEntity> entities);
}
