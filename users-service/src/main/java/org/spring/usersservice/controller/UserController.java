package org.spring.usersservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.spring.usersservice.dto.request.UserCreateDto;
import org.spring.usersservice.dto.response.UserInfoDto;
import org.spring.usersservice.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    ResponseEntity<UserInfoDto> find(
            @PathVariable("userId") @NotNull(message = "input id is empty") UUID id
    ) {
        return ResponseEntity.ok(userService.readUser(id));
    }

    @DeleteMapping("/{userId}")
    void delete(
            @PathVariable("userId") @NotNull(message = "input id is empty") UUID id
    ) {
        userService.delete(id);
    }

    @PostMapping
    ResponseEntity<UserInfoDto> register(
            @RequestBody @Valid UserCreateDto dto
    ) {
        return ResponseEntity.ok(userService.create(dto));
    }
}
