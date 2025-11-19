package org.spring.authservice.dto.input.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CardCreateDto(
        UUID token,
        String panEnd,
        String cardSupplier
) {
}
