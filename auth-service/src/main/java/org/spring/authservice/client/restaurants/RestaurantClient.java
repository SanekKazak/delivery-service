package org.spring.authservice.client.restaurants;

import org.spring.authservice.dto.input.order.OrderCreateDto;
import org.spring.authservice.dto.input.restaurants.ProviderOrderDetailsDto;
import org.spring.authservice.dto.input.restaurants.RestaurantCreateDto;
import org.spring.authservice.dto.input.restaurants.RestaurantUpdateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@FeignClient(name = "Restaurant-client", url = "http://localhost:8082/api/restaurants")
public interface RestaurantClient {
    @PostMapping("/{restaurantId}/logo")
    String logo(
            @PathVariable("restaurantId")  UUID restaurantId,
            @RequestParam("logo")  MultipartFile logo
    );
    @GetMapping
    String getAll(
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    );
    @GetMapping("/{restaurantId}")
    String get(
            @PathVariable("restaurantId") UUID restaurantId
    );
    @GetMapping("/details")
    OrderCreateDto getDetails(
            @RequestBody ProviderOrderDetailsDto dto
    );
    @PostMapping
    String create(
            @RequestBody RestaurantCreateDto dto
    );

    @PutMapping("/{restaurantId}")
    String update(
            @RequestBody RestaurantUpdateDto dto,
            @PathVariable("restaurantId") UUID restaurantId
    );
    @DeleteMapping("/{restaurantId}")
    void delete(
            @PathVariable("restaurantId") UUID restaurantId
    );
}
