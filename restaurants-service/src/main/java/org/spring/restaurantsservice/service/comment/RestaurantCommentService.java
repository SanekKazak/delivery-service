package org.spring.restaurantsservice.service.comment;

import org.spring.restaurantsservice.dto.request.comment.RestaurantCommentCreateDto;
import org.spring.restaurantsservice.dto.request.comment.RestaurantCommentUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantCommentInfoDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RestaurantCommentService {
    RestaurantCommentInfoDto create(RestaurantCommentCreateDto dto, UUID restaurantId);

    void delete(UUID commentId, UUID creatorId);

    List<RestaurantCommentInfoDto> getAllRestaurantComments(UUID restaurantId, Pageable pageable);

    List<RestaurantCommentInfoDto> getAllCreatorComments(UUID creatorId, Pageable pageable);

    RestaurantCommentInfoDto update(UUID commentId, RestaurantCommentUpdateDto update, UUID creatorId);
}
