package org.spring.authservice.client.restaurants;

import jakarta.validation.constraints.NotNull;
import org.spring.authservice.dto.input.restaurants.RestaurantCommentCreateDto;
import org.spring.authservice.dto.input.restaurants.RestaurantCommentUpdateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "Restaurant-comment-client", url = "http://localhost:8082/api/restaurants/comments")
public interface RestaurantCommentClient {
    @GetMapping("/{restaurantId}")
    String getAllCommentsByRestaurant(
            @PathVariable("restaurantId") UUID id,
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    );
    @GetMapping("/details/{creatorId}")
    String getAllCommentsByUser(
            @PathVariable("creatorId") UUID id,
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    );
    @PostMapping("/{restaurantId}")
    String create(
            @RequestBody RestaurantCommentCreateDto dto,
            @PathVariable("restaurantId") UUID id
    );
    @DeleteMapping("/{commentId}")
    String delete(
            @PathVariable("commentId") UUID commentId,
            @PathVariable("userId") UUID userId
    );
    @PutMapping("/{commentId}")
    String update(
            @RequestBody RestaurantCommentUpdateDto dto,
            @PathVariable("commentId") UUID commentId,
            @PathVariable("userId") UUID userId
    );
}
