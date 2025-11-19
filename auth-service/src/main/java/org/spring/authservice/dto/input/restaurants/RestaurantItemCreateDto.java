package org.spring.authservice.dto.input.restaurants;

public record RestaurantItemCreateDto(
        String name,
        Double price,
        String description
) {
}
