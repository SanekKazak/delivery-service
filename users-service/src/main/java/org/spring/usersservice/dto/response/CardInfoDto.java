package org.spring.usersservice.dto.response;

import java.util.UUID;

public record CardInfoDto(
        UUID id,
        UUID token,
        String panEnd,
        String cardSupplier
) {
}
