package org.spring.usersservice.service.card;

import org.spring.usersservice.dto.request.CardCreateDto;
import org.spring.usersservice.dto.response.CardInfoDto;

import java.util.List;
import java.util.UUID;

public interface CardService {
    CardInfoDto create(CardCreateDto dto, UUID userId);
    List<CardInfoDto> getCards(UUID userId);
    CardInfoDto getCard(UUID cardId);
    void deleteCard(UUID cardId, UUID userId);
}
