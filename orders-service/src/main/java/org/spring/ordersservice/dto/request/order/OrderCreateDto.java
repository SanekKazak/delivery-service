package org.spring.ordersservice.dto.request.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record OrderCreateDto (
        @NotEmpty(message = "no one items to buy") List<OrderItemCreateDto> items,
        @NotNull(message = "restaurant id is null") UUID restaurant,
        String restaurantName,
        @NotNull(message = "buyer id is null") UUID buyer
){}
