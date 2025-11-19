package org.spring.ordersservice.dto.response;

import java.util.UUID;

public record OrderItemInfoDto(
        UUID id,
        UUID restaurantItem,
        Integer quantity,
        String name,
        Double price
) {}
