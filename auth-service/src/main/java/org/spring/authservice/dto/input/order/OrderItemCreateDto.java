package org.spring.authservice.dto.input.order;

import java.util.UUID;

public record OrderItemCreateDto(
        UUID restaurantItem,
        Integer quantity,
        Double price,
        String name
) {
}
