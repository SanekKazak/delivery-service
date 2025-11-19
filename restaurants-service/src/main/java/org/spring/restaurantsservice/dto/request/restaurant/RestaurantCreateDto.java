package org.spring.restaurantsservice.dto.request.restaurant;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.spring.restaurantsservice.dto.request.item.RestaurantItemCreateDto;

import java.util.List;
import java.util.UUID;

public record RestaurantCreateDto(
        @NotEmpty(message = "name is null/empty") String name,
        List<RestaurantItemCreateDto> items,
        @NotEmpty(message = "description is null/empty") String description,
        @NotEmpty(message = "address is null/empty") String address,
        @NotNull(message = "owner id is null") UUID ownerId
) {
}
