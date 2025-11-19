package org.spring.authservice.controller.restaurants;

import lombok.RequiredArgsConstructor;
import org.spring.authservice.client.restaurants.RestaurantItemClient;
import org.spring.authservice.dto.input.restaurants.RestaurantItemCreateDto;
import org.spring.authservice.dto.input.restaurants.RestaurantItemUpdateDto;
import org.spring.authservice.exception.LockedAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants/items")
@RequiredArgsConstructor
public class RestaurantItemController {
    private final RestaurantItemClient client;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<String> getAllItems(
            @PathVariable("restaurantId") UUID restaurantId
    ) {
        return ResponseEntity.ok(client.getAllItems(restaurantId));
    }

    @PostMapping("/{itemId}/logo")
    public ResponseEntity<String> logo(
            @PathVariable("itemId") UUID itemId,
            @RequestParam("logo") MultipartFile logo
    ) {
        UUID managedRestaurant = UUID.randomUUID();
        return ResponseEntity.ok().body(client.logo(itemId, managedRestaurant, logo));
    }

    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody RestaurantItemCreateDto dto
    ) {
        UUID managedRestaurant = UUID.randomUUID();
        return ResponseEntity.ok(client.create(dto, managedRestaurant));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<String> update(
            @RequestBody RestaurantItemUpdateDto dto,
            @PathVariable("itemId") UUID itemId
    ) {
        UUID managedRestaurant = UUID.randomUUID();
        return ResponseEntity.ok(client.update(dto, itemId, managedRestaurant));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> delete(
            @PathVariable("itemId") UUID itemId
    ) {
        UUID managedRestaurant = UUID.randomUUID();
        client.delete(itemId, managedRestaurant);
        return ResponseEntity.ok().build();
    }
}
