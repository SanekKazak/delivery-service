package org.spring.restaurantsservice.dto.response.order;

import java.util.List;
import java.util.UUID;

public record OrderCreateDto(
        List<OrderItemCreateDto> items,
        UUID restaurant,
        String restaurantName
) {
}
