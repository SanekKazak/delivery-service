package org.spring.usersservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.spring.usersservice.dto.request.CardCreateDto;
import org.spring.usersservice.dto.response.CardInfoDto;
import org.spring.usersservice.service.card.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/{userId}")
    ResponseEntity<List<CardInfoDto>> findCards(
            @PathVariable("userId") @NotNull(message = "input id is empty") UUID id
    ) {
        return ResponseEntity.ok(cardService.getCards(id));
    }

    @GetMapping("/details/{cardId}")
    ResponseEntity<CardInfoDto> findCard(
            @PathVariable("cardId") @NotNull(message = "input id is empty") UUID id
    ) {
        return ResponseEntity.ok(cardService.getCard(id));
    }

    @PostMapping("/{userId}")
    ResponseEntity<CardInfoDto> createCard(
            @RequestBody @Valid CardCreateDto dto,
            @PathVariable("userId") @NotNull(message = "input id is empty") UUID id
    ) {
        return ResponseEntity.ok(cardService.create(dto, id));
    }

    @DeleteMapping("/{userId}/{cardId}")
    ResponseEntity<Void> deleteCard(
            @PathVariable("cardId") @NotNull(message = "input id is empty") UUID cardId,
            @PathVariable("userId") @NotNull(message = "input id is empty") UUID userId
    ) {
        cardService.deleteCard(cardId, userId);
        return ResponseEntity.ok().build();
    }
}
