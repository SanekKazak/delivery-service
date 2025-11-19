package org.spring.restaurantsservice.dto.request.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record ProviderOrderDetailsDto(
        @NotNull(message = "restaurant id is null") UUID restaurant,
        @NotEmpty(message = "no one items to find something") List<UUID> items
) {
}
