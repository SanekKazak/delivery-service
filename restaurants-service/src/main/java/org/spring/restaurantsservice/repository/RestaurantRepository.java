package org.spring.restaurantsservice.repository;

import org.spring.restaurantsservice.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {
    @Modifying
    @Query("UPDATE RestaurantEntity r SET r.avgStar = :avg WHERE r.id = :restaurantId")
    void updateAverageStars(@Param("restaurantId") UUID restaurantId, @Param("avg") Double avg);
}
