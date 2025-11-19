package org.spring.restaurantsservice.service.restaurant;

import org.spring.restaurantsservice.dto.request.order.ProviderOrderDetailsDto;
import org.spring.restaurantsservice.dto.request.restaurant.RestaurantCreateDto;
import org.spring.restaurantsservice.dto.request.restaurant.RestaurantUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantInfoDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantShortInfoDto;
import org.spring.restaurantsservice.dto.response.order.OrderCreateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    RestaurantInfoDto create(RestaurantCreateDto dto);

    List<RestaurantShortInfoDto> getAll(Pageable pageable);

    RestaurantInfoDto get(UUID restaurantId);

    RestaurantInfoDto update(RestaurantUpdateDto dto, UUID idPreviousEntity);

    void delete(UUID id);

    OrderCreateDto provideDetails(ProviderOrderDetailsDto dto);

    RestaurantInfoDto uploadLogo(UUID restaurantId, MultipartFile file);
}
