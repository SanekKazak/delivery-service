package org.spring.usersservice.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UserCreateDto(
        @NotEmpty(message = "login should be not null") String login
){
}
