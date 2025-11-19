package org.spring.authservice.controller.restaurants;

import lombok.RequiredArgsConstructor;
import org.spring.authservice.client.restaurants.RestaurantClient;
import org.spring.authservice.dto.input.restaurants.RestaurantCreateDto;
import org.spring.authservice.dto.input.restaurants.RestaurantUpdateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantClient client;

    @PostMapping("/{restaurantId}/logo")
    public ResponseEntity<String> logo(
            @PathVariable("restaurantId") UUID restaurantId,
            @RequestParam("logo") MultipartFile logo
    ) {
        return ResponseEntity.ok().body(client.logo(restaurantId, logo));
    }

    @GetMapping
    public ResponseEntity<String> getAll(
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    ) {
        return ResponseEntity.ok(client.getAll(pageable));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<String> get(
            @PathVariable("restaurantId") UUID restaurantId
    ) {
        return ResponseEntity.ok(client.get(restaurantId));
    }

    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody RestaurantCreateDto dto
    ) {
        return ResponseEntity.ok(client.create(dto));
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<String> update(
            @RequestBody RestaurantUpdateDto dto,
            @PathVariable("restaurantId") UUID restaurantId
    ) {
        return ResponseEntity.ok(client.update(dto, restaurantId));
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> delete(
            @PathVariable("restaurantId") UUID restaurantId
    ) {
        client.delete(restaurantId);
        return ResponseEntity.ok().build();
    }
}
