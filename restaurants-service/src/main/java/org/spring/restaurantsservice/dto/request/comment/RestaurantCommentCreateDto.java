package org.spring.restaurantsservice.dto.request.comment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RestaurantCommentCreateDto(
        @NotNull(message = "stars is null")
        @Min(value = 0, message = "less than 0")
        @Max(value = 5, message = "more than 5")
        Integer stars,
        String content,
        @NotNull(message = "creator undefined")
        UUID creator
) {
}
