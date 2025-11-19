package org.spring.authservice.controller.user;

import lombok.RequiredArgsConstructor;
import org.spring.authservice.client.user.UserClient;
import org.spring.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserClient client;
    private final AuthService authService;

    @GetMapping
    ResponseEntity<String> find() {
        UUID userId = UUID.randomUUID();
        return ResponseEntity.ok(client.find(userId));
    }

    @DeleteMapping
    ResponseEntity<Void> delete() {
        UUID userId = UUID.randomUUID();
        authService.delete(userId);
        return ResponseEntity.ok().build();
    }
}
