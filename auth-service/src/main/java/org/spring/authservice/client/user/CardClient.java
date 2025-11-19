package org.spring.authservice.client.user;

import org.spring.authservice.dto.input.user.CardCreateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "card-client", url = "http://localhost:8080/api/cards")
public interface CardClient {
    @GetMapping("/{userId}")
    String findCards(@PathVariable("userId") UUID id);

    @GetMapping("/details/{userId}/{cardId}")
    String findCard(@PathVariable("cardId") UUID cardId, @PathVariable("userId") UUID userId);

    @PostMapping("/{userId}")
    String createCard(@RequestBody CardCreateDto dto, @PathVariable("userId") UUID id);

    @DeleteMapping("/{userId}/{cardId}")
    void deleteCard(@PathVariable("cardId") UUID cardId, @PathVariable("userId") UUID userId);
}
