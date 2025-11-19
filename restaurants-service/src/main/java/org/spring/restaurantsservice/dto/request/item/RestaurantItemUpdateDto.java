package org.spring.restaurantsservice.dto.request.item;


public record RestaurantItemUpdateDto(
        String newName,
        Double newPrice,
        String description
) {
}
