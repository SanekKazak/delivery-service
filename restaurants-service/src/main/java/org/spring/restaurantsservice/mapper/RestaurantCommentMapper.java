package org.spring.restaurantsservice.mapper;

import org.mapstruct.*;
import org.spring.restaurantsservice.dto.request.comment.RestaurantCommentCreateDto;
import org.spring.restaurantsservice.dto.request.comment.RestaurantCommentUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantCommentInfoDto;
import org.spring.restaurantsservice.entity.RestaurantCommentEntity;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RestaurantCommentMapper {
    @Mapping(target = "stars", source = "stars")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "creator", source = "creator")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    RestaurantCommentEntity toEntity(RestaurantCommentCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "stars", source = "stars")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "creator", source = "creator")
    @Mapping(target = "created", source = "created")
    RestaurantCommentInfoDto toDto(RestaurantCommentEntity entities);

    @Mapping(target = "stars", source = "stars")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    void update(@MappingTarget RestaurantCommentEntity entity, RestaurantCommentUpdateDto dto);

    default Double fromDoubleToInteger(Integer integer) {
        return Double.valueOf(integer);
    }

    List<RestaurantCommentInfoDto> toDtos(List<RestaurantCommentEntity> entities);
}
