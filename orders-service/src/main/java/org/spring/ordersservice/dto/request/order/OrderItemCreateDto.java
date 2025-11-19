package org.spring.ordersservice.dto.request.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderItemCreateDto (
        @NotNull(message = "restaurant id is null") UUID restaurantItem,
        @NotNull(message = "quantity is null") Integer quantity,
        @NotNull(message = "price is null") Double price,
        @NotEmpty(message = "name is null") String name
) {
}
