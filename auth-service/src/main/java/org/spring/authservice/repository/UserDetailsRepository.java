package org.spring.authservice.repository;

import org.spring.authservice.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, UUID> {
    Optional<UserDetailsEntity> findByLogin(String login);

    boolean existsByLogin(String login);

    Optional<UserDetailsEntity> findByReferenceId(UUID referenceId);

    void deleteByReferenceId(UUID referenceId);
}
