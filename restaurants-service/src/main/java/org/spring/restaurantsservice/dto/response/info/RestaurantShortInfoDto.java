package org.spring.restaurantsservice.dto.response.info;

import java.util.UUID;

public record RestaurantShortInfoDto(
        UUID id,
        String name,
        Double avgStar,
        String logoBase64
) {
}
