package org.spring.ordersservice.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderInfoDto (
        UUID id,
        UUID restaurant,
        Double totalPrice,
        List<OrderItemInfoDto> items,
        String restaurantName,
        Instant created
) {}
