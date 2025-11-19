package org.spring.authservice.dto.input.restaurants;

import java.util.List;
import java.util.UUID;

public record RestaurantCreateDto(
        String name,
        String description,
        List<RestaurantItemCreateDto> items,
        String address,
        UUID ownerId
) {
}
