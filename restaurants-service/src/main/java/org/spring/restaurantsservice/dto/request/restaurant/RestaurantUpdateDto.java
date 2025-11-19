package org.spring.restaurantsservice.dto.request.restaurant;

public record RestaurantUpdateDto(
        String name,
        String description,
        String address
) {
}
