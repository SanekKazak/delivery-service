package org.spring.restaurantsservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.spring.restaurantsservice.dto.request.order.ProviderOrderDetailsDto;
import org.spring.restaurantsservice.dto.request.restaurant.RestaurantCreateDto;
import org.spring.restaurantsservice.dto.request.restaurant.RestaurantUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantInfoDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantShortInfoDto;
import org.spring.restaurantsservice.dto.response.order.OrderCreateDto;
import org.spring.restaurantsservice.service.restaurant.RestaurantService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/{restaurantId}/logo")
    public ResponseEntity<RestaurantInfoDto> logo(
            @PathVariable("restaurantId") @NotNull(message = "restaurant Id is null") UUID restaurantId,
            @RequestParam("logo") @NotNull(message = "nothing to save, logo is null") MultipartFile logo
    ) {
        return ResponseEntity.ok().body(restaurantService.uploadLogo(restaurantId, logo));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantShortInfoDto>> getAll(
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    ) {
        return ResponseEntity.ok(restaurantService.getAll(pageable));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantInfoDto> get(
            @PathVariable("restaurantId") @NotNull(message = "restaurant Id is null") UUID restaurantId
    ) {
        return ResponseEntity.ok(restaurantService.get(restaurantId));
    }

    @GetMapping("/details")
    public ResponseEntity<OrderCreateDto> getDetails(
            @RequestBody @Valid ProviderOrderDetailsDto dto
    ) {
        return ResponseEntity.ok(restaurantService.provideDetails(dto));
    }

    @PostMapping
    public ResponseEntity<RestaurantInfoDto> create(@RequestBody @Valid RestaurantCreateDto dto) {
        return ResponseEntity.ok(restaurantService.create(dto));
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantInfoDto> update(
            @RequestBody @Valid RestaurantUpdateDto dto,
            @PathVariable("restaurantId") @NotNull(message = "restaurant Id is null") UUID restaurantId
    ) {
        return ResponseEntity.ok(restaurantService.update(dto, restaurantId));
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> delete(
            @PathVariable("restaurantId") @NotNull(message = "restaurant Id is null") UUID restaurantId
    ) {
        restaurantService.delete(restaurantId);
        return ResponseEntity.ok().build();
    }
}
