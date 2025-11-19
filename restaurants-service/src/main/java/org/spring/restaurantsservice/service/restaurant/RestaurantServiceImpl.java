package org.spring.restaurantsservice.service.restaurant;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.spring.restaurantsservice.dto.request.order.ProviderOrderDetailsDto;
import org.spring.restaurantsservice.dto.request.restaurant.RestaurantCreateDto;
import org.spring.restaurantsservice.dto.request.restaurant.RestaurantUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantInfoDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantShortInfoDto;
import org.spring.restaurantsservice.dto.response.order.OrderCreateDto;
import org.spring.restaurantsservice.dto.response.order.OrderItemCreateDto;
import org.spring.restaurantsservice.entity.RestaurantEntity;
import org.spring.restaurantsservice.entity.RestaurantItemEntity;
import org.spring.restaurantsservice.exception.FileExtraSizeException;
import org.spring.restaurantsservice.exception.NotFoundEntityByIdException;
import org.spring.restaurantsservice.mapper.RestaurantMapper;
import org.spring.restaurantsservice.repository.RestaurantRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMapper mapper;
    private final RestaurantRepository repository;
    private final long logo_size = 1 * 1024 * 1024;

    @Override
    public RestaurantInfoDto uploadLogo(UUID restaurantId, MultipartFile file) {
        if (file.getSize() > logo_size) {
            throw new FileExtraSizeException("logo size is larger than logo size provide: " + file.getSize() + " | required :" + logo_size);
        }
        RestaurantEntity restaurantEntity = repository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cannot find restaurant with id " + restaurantId));
        try {
            byte[] logo = file.getBytes();
            restaurantEntity.setImage(logo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RestaurantEntity save = repository.save(restaurantEntity);
        return mapper.fromEntityToInfoDto(save);
    }

    @Override
    public RestaurantInfoDto get(UUID restaurantId) {
        RestaurantEntity restaurantEntity = repository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cant't find restaurant with id " + restaurantId));
        return mapper.fromEntityToInfoDto(restaurantEntity);
    }

    @Override
    public List<RestaurantShortInfoDto> getAll(Pageable pageable) {
        List<RestaurantEntity> allRestaurants = repository.findAll(pageable).getContent();
        return mapper.fromEntitiesToShortInfoDtos(allRestaurants);
    }

    @Override
    public OrderCreateDto provideDetails(ProviderOrderDetailsDto dto) {
        RestaurantEntity restaurant = repository.findById(dto.restaurant())
                .orElseThrow(() -> new NotFoundEntityByIdException("cant find restaurant by id:" + dto.restaurant()));
        List<RestaurantItemEntity> items = restaurant.getItems();
        List<OrderItemCreateDto> list = items.stream().map(mapper::fromEntityToCreateDto).toList();
        return new OrderCreateDto(
                list,
                restaurant.getId(),
                restaurant.getName()
        );
    }

    @Override
    @Transactional
    public RestaurantInfoDto create(RestaurantCreateDto dto) {
        RestaurantEntity restaurantEntity = mapper.fromCreateDtoToEntity(dto);
        List<RestaurantItemEntity> items = restaurantEntity.getItems();
        restaurantEntity.setItems(items);
        restaurantEntity.setAvgStar(5.0);
        RestaurantEntity save = repository.save(restaurantEntity);
        return mapper.fromEntityToInfoDto(save);
    }

    @Override
    @Transactional
    public RestaurantInfoDto update(RestaurantUpdateDto dto, UUID restaurantId) {
        RestaurantEntity restaurant = repository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cant find restaurant by id: " + restaurantId));
        mapper.updateEntity(dto, restaurant);
        RestaurantEntity save = repository.save(restaurant);
        return mapper.fromEntityToInfoDto(save);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
