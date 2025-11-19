package org.spring.restaurantsservice.dto.response.info;

import java.util.UUID;

public record RestaurantItemInfoDto(
        UUID id,
        String name,
        Double price,
        String description,
        String logoBase64
) {
}