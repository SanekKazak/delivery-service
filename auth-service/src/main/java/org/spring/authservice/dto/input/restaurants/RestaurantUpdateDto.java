package org.spring.authservice.dto.input.restaurants;

import java.util.List;

public record RestaurantUpdateDto(
        String name,
        String description,
        String address
) {
}
