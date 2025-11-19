package org.spring.restaurantsservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.spring.restaurantsservice.dto.request.comment.RestaurantCommentCreateDto;
import org.spring.restaurantsservice.dto.request.comment.RestaurantCommentUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantCommentInfoDto;
import org.spring.restaurantsservice.service.comment.RestaurantCommentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants/comments")
@RequiredArgsConstructor
public class RestaurantCommentController {
    private final RestaurantCommentService restaurantCommentService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<RestaurantCommentInfoDto>> getAllCommentsByRestaurant(
            @PathVariable("restaurantId") @NotNull(message = "id is null") UUID id,
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    ) {
        return ResponseEntity.ok(restaurantCommentService.getAllRestaurantComments(id, pageable));
    }

    @GetMapping("/details/{creatorId}")
    public ResponseEntity<List<RestaurantCommentInfoDto>> getAllCommentsByUser(
            @PathVariable("creatorId") @NotNull(message = "id is null") UUID id,
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    ) {
        return ResponseEntity.ok(restaurantCommentService.getAllCreatorComments(id, pageable));
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<RestaurantCommentInfoDto> create(
            @RequestBody @Valid RestaurantCommentCreateDto dto,
            @PathVariable("restaurantId") @NotNull(message = "id is null") UUID id
    ) {
        return ResponseEntity.ok(restaurantCommentService.create(dto, id));
    }

    @DeleteMapping("/{userId}/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable("commentId") @NotNull(message = "id is null") UUID commentId,
            @PathVariable("userId") @NotNull(message = "id is null") UUID userId
    ) {
        restaurantCommentService.delete(commentId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/{commentId}")
    public ResponseEntity<RestaurantCommentInfoDto> update(
            @RequestBody RestaurantCommentUpdateDto dto,
            @PathVariable("commentId") @NotNull(message = "id is null") UUID commentId,
            @PathVariable("userId") @NotNull(message = "id is null") UUID userId
    ) {
        return ResponseEntity.ok(restaurantCommentService.update(commentId, dto, userId));
    }
}
