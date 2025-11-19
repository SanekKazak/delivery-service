package org.spring.restaurantsservice.dto.request.comment;

public record RestaurantCommentUpdateDto(
        Integer stars,
        String content
) {
}
