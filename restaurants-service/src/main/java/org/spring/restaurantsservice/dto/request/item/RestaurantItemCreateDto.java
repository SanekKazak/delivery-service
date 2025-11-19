package org.spring.restaurantsservice.dto.request.item;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RestaurantItemCreateDto(
        @NotEmpty(message = "name is null/empty") String name,
        @NotNull(message = "price not defined") Double price,
        String description
) {
}
