package org.spring.restaurantsservice.repository;

import org.spring.restaurantsservice.entity.RestaurantCommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RestaurantCommentRepository extends JpaRepository<RestaurantCommentEntity, UUID> {
    Page<RestaurantCommentEntity> findAllByCreator(UUID creatorId, Pageable pageable);

    Page<RestaurantCommentEntity> findAllByRestaurantId(UUID restaurantId, Pageable pageable);

    @Query("""
            select avg(c.stars)
            from RestaurantCommentEntity c
            where c.restaurant.id = :restaurantId
            """)
    Double findAverageStarsByRestaurantId(@Param("restaurantId") UUID restaurantId);
}
