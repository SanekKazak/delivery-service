package org.spring.authservice.repository;

import org.spring.authservice.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<UserRoleEntity, UUID> {
}
