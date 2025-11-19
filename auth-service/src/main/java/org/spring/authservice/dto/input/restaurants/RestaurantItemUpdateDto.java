package org.spring.authservice.dto.input.restaurants;


public record RestaurantItemUpdateDto(
        String newName,
        Double newPrice,
        String description
) {
}
