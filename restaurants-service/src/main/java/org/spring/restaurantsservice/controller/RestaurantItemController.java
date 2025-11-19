package org.spring.restaurantsservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.spring.restaurantsservice.dto.request.item.RestaurantItemCreateDto;
import org.spring.restaurantsservice.dto.request.item.RestaurantItemUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantItemInfoDto;
import org.spring.restaurantsservice.service.item.RestaurantItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants/items")
@RequiredArgsConstructor
public class RestaurantItemController {
    private final RestaurantItemService restaurantItemService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<RestaurantItemInfoDto>> getAllItems(
            @PathVariable("restaurantId") @NotNull(message = "restaurant Id is null") UUID restaurantId
    ) {
        return ResponseEntity.ok(restaurantItemService.getAllRestaurantItems(restaurantId));
    }

    @PostMapping("/{restaurantId}/{itemId}/logo")
    public ResponseEntity<RestaurantItemInfoDto> logo(
            @PathVariable("itemId") @NotNull(message = "item Id is null") UUID itemId,
            @PathVariable("restaurantId") @NotNull(message = "restaurant Id is null") UUID restaurantId,
            @RequestParam("logo") @NotNull(message = "nothing to save, logo is null") MultipartFile logo
    ) {
        return ResponseEntity.ok().body(restaurantItemService.uploadLogo(itemId, restaurantId, logo));
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<RestaurantItemInfoDto> create(
            @RequestBody @Valid RestaurantItemCreateDto dto,
            @PathVariable("restaurantId") @NotNull(message = "restaurant Id is null") UUID restaurantId
    ) {
        return ResponseEntity.ok(restaurantItemService.create(dto, restaurantId));
    }

    @PutMapping("/{restaurantId}/{itemId}")
    public ResponseEntity<RestaurantItemInfoDto> update(
            @RequestBody @Valid RestaurantItemUpdateDto dto,
            @PathVariable("restaurantId") @NotNull(message = "restaurant Id is null") UUID restaurantId,
            @PathVariable("itemId") @NotNull(message = "item Id is null") UUID itemId) {
        return ResponseEntity.ok(restaurantItemService.update(dto, itemId, restaurantId));
    }

    @DeleteMapping("/{restaurantId}/{itemId}")
    public void delete(
            @PathVariable("itemId") @NotNull(message = "item Id is null") UUID itemId,
            @PathVariable("restaurantId") @NotNull(message = "restaurant Id is null") UUID restaurantId
    ) {
        restaurantItemService.delete(itemId, restaurantId);
    }
}
