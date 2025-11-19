package org.spring.authservice.controller.restaurants;

import lombok.RequiredArgsConstructor;
import org.spring.authservice.client.restaurants.RestaurantCommentClient;
import org.spring.authservice.dto.input.restaurants.RestaurantCommentCreateDto;
import org.spring.authservice.dto.input.restaurants.RestaurantCommentUpdateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants/comments")
@RequiredArgsConstructor
public class RestaurantCommentController {
    private final RestaurantCommentClient client;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<String> getAllCommentsByRestaurant(
            @PathVariable("restaurantId") UUID restaurantId,
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    ) {
        return ResponseEntity.ok(client.getAllCommentsByRestaurant(restaurantId, pageable));
    }

    @GetMapping("/details")
    public ResponseEntity<String> getAllCommentsByUser(
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    ) {
        UUID userId = UUID.randomUUID();
        return ResponseEntity.ok(client.getAllCommentsByUser(userId, pageable));
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<String> create(
            @RequestBody RestaurantCommentCreateDto dto,
            @PathVariable("restaurantId") UUID restaurantId
    ) {
        UUID userId = UUID.randomUUID();
        return ResponseEntity.ok(client.create(dto, restaurantId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable("commentId") UUID commentId
    ) {
        UUID userId = UUID.randomUUID();
        client.delete(commentId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> update(
            @RequestBody RestaurantCommentUpdateDto dto,
            @PathVariable("commentId") UUID commentId
    ) {
        UUID userId = UUID.randomUUID();
        return ResponseEntity.ok(client.update(dto, commentId, userId));
    }
}
