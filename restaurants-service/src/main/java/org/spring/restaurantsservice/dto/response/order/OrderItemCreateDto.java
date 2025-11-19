package org.spring.restaurantsservice.dto.response.order;

import java.util.UUID;

public record OrderItemCreateDto(
        UUID restaurantItem,
        Double price,
        String name
) {
}
