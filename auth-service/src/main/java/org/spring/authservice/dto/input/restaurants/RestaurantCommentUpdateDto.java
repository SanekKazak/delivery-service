package org.spring.authservice.dto.input.restaurants;

public record RestaurantCommentUpdateDto(
        Integer stars,
        String content
) {
}
