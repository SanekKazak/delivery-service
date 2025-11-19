package org.spring.ordersservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.spring.ordersservice.dto.request.order.OrderItemCreateDto;
import org.spring.ordersservice.dto.response.OrderItemInfoDto;
import org.spring.ordersservice.entity.OrderItemEntity;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderItemMapper {
    @Mapping(target = "restaurantItem", source = "restaurantItem")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItemEntity fromItemCreateDtoToEntity(OrderItemCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "restaurantItem", source = "restaurantItem")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "name", source = "name")
    OrderItemInfoDto fromEntityToDto(OrderItemEntity entity);


    List<OrderItemEntity> fromItemsCreateDtoToEntities(List<OrderItemCreateDto> dtos);

    List<OrderItemInfoDto> fromEntitiesToDtos(List<OrderItemEntity> entity);

}
