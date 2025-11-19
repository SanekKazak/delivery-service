package org.spring.authservice.dto.input.restaurants;

import java.util.List;
import java.util.UUID;

public record ProviderOrderDetailsDto(
        UUID restaurant,
        List<UUID> items
) {
}
