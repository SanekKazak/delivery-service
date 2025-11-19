package org.spring.restaurantsservice.service.item;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.spring.restaurantsservice.dto.request.item.RestaurantItemCreateDto;
import org.spring.restaurantsservice.dto.request.item.RestaurantItemUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantItemInfoDto;
import org.spring.restaurantsservice.entity.RestaurantEntity;
import org.spring.restaurantsservice.entity.RestaurantItemEntity;
import org.spring.restaurantsservice.exception.FileExtraSizeException;
import org.spring.restaurantsservice.exception.LockedAccessException;
import org.spring.restaurantsservice.exception.NotFoundEntityByIdException;
import org.spring.restaurantsservice.mapper.RestaurantMapper;
import org.spring.restaurantsservice.repository.RestaurantItemRepository;
import org.spring.restaurantsservice.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantItemServiceImpl implements RestaurantItemService {
    private final RestaurantItemRepository itemRepository;
    private final RestaurantMapper mapper;
    private final RestaurantRepository restaurantRepository;
    private final long logo_size = 1 * 1024 * 1024;

    @Override
    public RestaurantItemInfoDto uploadLogo(UUID itemId, UUID restaurantId, MultipartFile file) {
        if (file.getSize() > logo_size) {
            throw new FileExtraSizeException("logo size is larger than logo size provide: " + file.getSize() + " | required :" + logo_size);
        }
        RestaurantItemEntity restaurantItemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cannot find item with id " + itemId));
        if(!restaurantItemEntity.getRestaurant().getId().equals(restaurantId)){
            throw new LockedAccessException("cannot access restaurant item you are not owner");
        }
        try {
            byte[] logo = file.getBytes();
            restaurantItemEntity.setImage(logo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RestaurantItemEntity save = itemRepository.save(restaurantItemEntity);
        return mapper.fromEntityToInfoDto(save);
    }

    @Override
    public List<RestaurantItemInfoDto> getAllRestaurantItems(UUID restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cant find restaurant by id:" + restaurantId));
        List<RestaurantItemEntity> items = restaurant.getItems();
        return mapper.fromEntitiesToInfoItems(items);
    }

    @Override
    @Transactional
    public RestaurantItemInfoDto create(RestaurantItemCreateDto dto, UUID restaurantId) {
        RestaurantItemEntity restaurantItemEntity = mapper.fromCreateDtoToEntity(dto);

        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cant find restaurant by id: " + restaurantId));
        restaurant.addItem(restaurantItemEntity);
        RestaurantItemEntity save = itemRepository.saveAndFlush(restaurantItemEntity);
        return mapper.fromEntityToInfoDto(save);
    }

    @Override
    @Transactional
    public RestaurantItemInfoDto update(RestaurantItemUpdateDto dto, UUID itemId, UUID restaurantId) {
        RestaurantItemEntity item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cant find item by id: " + itemId));

        if(!item.getRestaurant().getId().equals(restaurantId)){
            throw new LockedAccessException("cannot access restaurant item you are not owner");
        }
        mapper.updateEntity(dto, item);
        return mapper.fromEntityToInfoDto(item);
    }

    @Override
    @Transactional
    public void delete(UUID itemId, UUID restaurantId) {
        RestaurantItemEntity item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cant find item by id: " + itemId));
        if(!item.getRestaurant().getId().equals(restaurantId)){
            throw new LockedAccessException("cannot access restaurant item you are not owner");
        }
        itemRepository.deleteById(itemId);
    }
}
