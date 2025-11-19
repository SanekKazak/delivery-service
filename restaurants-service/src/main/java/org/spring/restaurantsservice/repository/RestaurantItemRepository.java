package org.spring.restaurantsservice.repository;

import org.spring.restaurantsservice.entity.RestaurantItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantItemRepository extends JpaRepository<RestaurantItemEntity, UUID> {
}
