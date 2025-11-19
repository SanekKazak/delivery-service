package org.spring.restaurantsservice.dto.response.info;

import java.time.Instant;
import java.util.UUID;

public record RestaurantCommentInfoDto(
        UUID id,
        Double stars,
        String content,
        UUID creator,
        Instant created
) {
}
