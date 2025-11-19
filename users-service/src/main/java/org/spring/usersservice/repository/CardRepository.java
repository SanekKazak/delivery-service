package org.spring.usersservice.repository;

import org.spring.usersservice.entity.CardEntity;
import org.spring.usersservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    List<CardEntity> findByUserId(UUID userId);
    UUID user(UserEntity user);
}
