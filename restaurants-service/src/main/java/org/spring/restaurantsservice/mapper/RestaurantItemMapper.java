package org.spring.restaurantsservice.mapper;

import org.mapstruct.*;
import org.spring.restaurantsservice.dto.request.item.RestaurantItemCreateDto;
import org.spring.restaurantsservice.dto.request.item.RestaurantItemUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantItemInfoDto;
import org.spring.restaurantsservice.dto.response.order.OrderItemCreateDto;
import org.spring.restaurantsservice.entity.RestaurantItemEntity;

import java.util.Base64;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RestaurantItemMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    RestaurantItemEntity fromCreateDtoToEntity(RestaurantItemCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "logoBase64", source = "image")
    RestaurantItemInfoDto fromEntityToInfoDto(RestaurantItemEntity entity);

    @Mapping(target = "name", source = "newName")
    @Mapping(target = "price", source = "newPrice")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    void updateEntity(RestaurantItemUpdateDto dto, @MappingTarget RestaurantItemEntity entity);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "restaurantItem", source = "id")
    OrderItemCreateDto fromEntityToCreateDto(RestaurantItemEntity entity);

    List<RestaurantItemInfoDto> fromEntitiesToInfoItems(List<RestaurantItemEntity> entities);

    default String fromFileToBytes(byte[] image) {
        if (image != null) {
            return Base64.getEncoder().encodeToString(image);
        }
        return null;
    }
}
