package org.spring.authservice.client.restaurants;

import jakarta.validation.constraints.NotNull;
import org.spring.authservice.dto.input.restaurants.RestaurantItemCreateDto;
import org.spring.authservice.dto.input.restaurants.RestaurantItemUpdateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@FeignClient(name = "Restaurant-item-client", url = "http://localhost:8082/api/restaurants/items")
public interface RestaurantItemClient {
    @GetMapping("/{restaurantId}")
    String getAllItems(
            @PathVariable("restaurantId") UUID restaurantId
    );
    @PostMapping("/{restaurantId}/{itemId}/logo")
    String logo(
            @PathVariable("itemId") UUID itemId,
            @PathVariable("restaurantId") UUID restaurantId,
            @RequestParam("logo") MultipartFile logo
    );
    @PostMapping("/{restaurantId}")
    String create(
            @RequestBody RestaurantItemCreateDto dto,
            @PathVariable("restaurantId") UUID restaurantId
    );
    @PutMapping("/{restaurantId}/{itemId}")
    String update(
            @RequestBody RestaurantItemUpdateDto dto,
            @PathVariable("itemId") UUID itemId,
            @PathVariable("restaurantId") UUID restaurantId
    );
    @DeleteMapping("/{restaurantId}/{itemId}")
    void delete(
            @PathVariable("itemId") UUID itemId,
            @PathVariable("restaurantId") UUID restaurantId
    );
}
