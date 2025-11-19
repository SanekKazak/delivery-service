package org.spring.restaurantsservice.service.item;

import org.spring.restaurantsservice.dto.request.item.RestaurantItemCreateDto;
import org.spring.restaurantsservice.dto.request.item.RestaurantItemUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantItemInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


public interface RestaurantItemService {
    RestaurantItemInfoDto create(RestaurantItemCreateDto dto, UUID restaurantId);

    RestaurantItemInfoDto update(RestaurantItemUpdateDto dto, UUID idPreviousEntity, UUID restaurantId);

    void delete(UUID itemId, UUID restaurantId);

    List<RestaurantItemInfoDto> getAllRestaurantItems(UUID restaurantId);

    RestaurantItemInfoDto uploadLogo(UUID itemId, UUID restaurantId, MultipartFile file);
}
