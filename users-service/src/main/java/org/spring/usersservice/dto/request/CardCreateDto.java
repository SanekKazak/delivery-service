package org.spring.usersservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CardCreateDto(
        @NotNull(message = "token should be already prepared") UUID token,
        @NotEmpty(message = "end of pan should be not null") String panEnd,
        String cardSupplier
) {
}
