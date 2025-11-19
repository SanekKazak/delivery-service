package org.spring.restaurantsservice.mapper;

import org.mapstruct.*;
import org.spring.restaurantsservice.dto.request.restaurant.RestaurantCreateDto;
import org.spring.restaurantsservice.dto.request.restaurant.RestaurantUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantInfoDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantShortInfoDto;
import org.spring.restaurantsservice.entity.RestaurantEntity;

import java.util.Base64;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RestaurantMapper extends RestaurantItemMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "items", source = "items")
    @Mapping(target = "ownerId", source = "ownerId")
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "avgStar", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    RestaurantEntity fromCreateDtoToEntity(RestaurantCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "avgStar", source = "avgStar")
    @Mapping(target = "logoBase64", source = "image")
    RestaurantShortInfoDto fromEntityToShortInfoDto(RestaurantEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "avgStar", source = "avgStar")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "logoBase64", source = "image")
    RestaurantInfoDto fromEntityToInfoDto(RestaurantEntity entity);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "avgStar", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    void updateEntity(RestaurantUpdateDto dto, @MappingTarget RestaurantEntity entity);

    List<RestaurantShortInfoDto> fromEntitiesToShortInfoDtos(List<RestaurantEntity> entities);

    default String fromFileToBytes(byte[] image) {
        if (image != null) {
            return Base64.getEncoder().encodeToString(image);
        }
        return null;
    }
}
