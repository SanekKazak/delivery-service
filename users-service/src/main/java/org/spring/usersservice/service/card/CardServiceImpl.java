package org.spring.usersservice.service.card;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.spring.usersservice.dto.request.CardCreateDto;
import org.spring.usersservice.dto.response.CardInfoDto;
import org.spring.usersservice.entity.CardEntity;
import org.spring.usersservice.entity.UserEntity;
import org.spring.usersservice.exception.NotFoundEntityByIdException;
import org.spring.usersservice.mapper.CardMapper;
import org.spring.usersservice.repository.CardRepository;
import org.spring.usersservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardMapper mapper;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CardInfoDto create(CardCreateDto dto, UUID userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundEntityByIdException("cannot find user with id: " + userId));
        CardEntity cardEntity = mapper.fromCreateDtoToEntity(dto);
        userEntity.addCard(cardEntity);
        CardEntity save = cardRepository.saveAndFlush(cardEntity);
        return mapper.fromEntityToInfoDto(save);
    }

    @Override
    public List<CardInfoDto> getCards(UUID userId) {
        List<CardEntity> cardsByUserId = cardRepository.findByUserId(userId);
        return mapper.fromEntitiesToInfoDtos(cardsByUserId);
    }

    @Override
    public CardInfoDto getCard(UUID cardId) {
        CardEntity card = cardRepository.findById(cardId)
                .orElseThrow(()-> new NotFoundEntityByIdException("cant find card with id: " + cardId));
        return mapper.fromEntityToInfoDto(card);
    }

    @Override
    public void deleteCard(UUID cardId, UUID userId) {
        CardEntity cardEntity = cardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cant find card with id: " + cardId));
        if(cardEntity.getUser().getId().equals(userId)){
            cardRepository.deleteById(cardId);
            return;
        }
        throw new NotFoundEntityByIdException("cant delete card with id: " + cardId);
    }
}
