package org.spring.authservice.controller.user;


import lombok.RequiredArgsConstructor;
import org.spring.authservice.client.user.CardClient;
import org.spring.authservice.dto.input.user.CardCreateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardClient client;

    @GetMapping
    ResponseEntity<String> findCards() {
        UUID userId = UUID.randomUUID();
        return ResponseEntity.ok(client.findCards(userId));
    }

    @GetMapping("/details/{cardId}")
    ResponseEntity<String> findCard(
            @PathVariable("cardId") UUID cardId
    ) {
        UUID userId = UUID.randomUUID();
        return ResponseEntity.ok(client.findCard(cardId, userId));
    }

    @PostMapping
    ResponseEntity<String> createCard(
            @RequestBody CardCreateDto dto
    ) {
        UUID userId = UUID.randomUUID();
        return ResponseEntity.ok(client.createCard(dto, userId));
    }

    @DeleteMapping("/{cardId}")
    ResponseEntity<Void> deleteCard(
            @PathVariable("cardId") UUID cardId
    ) {
        UUID userId = UUID.randomUUID();
        client.deleteCard(cardId, userId);
        return ResponseEntity.ok().build();
    }
}
