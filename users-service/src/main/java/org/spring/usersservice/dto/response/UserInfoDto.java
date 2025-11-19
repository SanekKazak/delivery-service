package org.spring.usersservice.dto.response;

import java.util.UUID;

public record UserInfoDto (
        UUID id,
        String login
) {
}
