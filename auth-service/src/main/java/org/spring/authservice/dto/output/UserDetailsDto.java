package org.spring.authservice.dto.output;

import java.util.List;
import java.util.UUID;

public record UserDetailsDto(
        UUID id,
        String login,
        List<String> roles
) {
}
