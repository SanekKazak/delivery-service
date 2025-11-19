package org.spring.authservice.dto.input.auth;

import jakarta.validation.constraints.NotEmpty;

public record UserCredentialsDto(
        @NotEmpty(message = "provided login is null") String login,
        @NotEmpty(message = "provided login is null") String password
) {
}
