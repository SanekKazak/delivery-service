package org.spring.ordersservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.spring.ordersservice.dto.request.order.OrderCreateDto;
import org.spring.ordersservice.dto.response.OrderInfoDto;
import org.spring.ordersservice.entity.OrderEntity;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderMapper extends OrderItemMapper {
    @Mapping(target = "restaurant", source = "restaurant")
    @Mapping(target = "restaurantName", source = "restaurantName")
    @Mapping(target = "items", source = "items")
    @Mapping(target = "buyer", source = "buyer")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    OrderEntity fromCreateToEntity(OrderCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "restaurant", source = "restaurant")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "items", source = "items")
    @Mapping(target = "restaurantName", source = "restaurantName")
    @Mapping(target = "created", source = "created")
    OrderInfoDto fromEntityToInfoDto(OrderEntity entity);

    List<OrderInfoDto> fromEntitiesToInfoDtos(List<OrderEntity> entity);
}
