package org.spring.restaurantsservice.dto.response.info;

import java.util.UUID;

public record RestaurantInfoDto(
        UUID id,
        String name,
        String address,
        String description,
        String logoBase64,
        Double avgStar
) {}
