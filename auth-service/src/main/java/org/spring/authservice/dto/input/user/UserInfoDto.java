package org.spring.authservice.dto.input.user;

import java.util.UUID;

public record UserInfoDto(
        UUID id,
        String login
) {
}
