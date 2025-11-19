package org.spring.restaurantsservice.service.comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.spring.restaurantsservice.dto.request.comment.RestaurantCommentCreateDto;
import org.spring.restaurantsservice.dto.request.comment.RestaurantCommentUpdateDto;
import org.spring.restaurantsservice.dto.response.info.RestaurantCommentInfoDto;
import org.spring.restaurantsservice.entity.RestaurantCommentEntity;
import org.spring.restaurantsservice.entity.RestaurantEntity;
import org.spring.restaurantsservice.exception.LockedAccessException;
import org.spring.restaurantsservice.exception.NotFoundEntityByIdException;
import org.spring.restaurantsservice.mapper.RestaurantCommentMapper;
import org.spring.restaurantsservice.repository.RestaurantCommentRepository;
import org.spring.restaurantsservice.repository.RestaurantRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantCommentServiceImpl implements RestaurantCommentService {
    private final RestaurantCommentMapper restaurantCommentMapper;
    private final RestaurantCommentRepository restaurantCommentRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public RestaurantCommentInfoDto update(UUID commentId, RestaurantCommentUpdateDto update, UUID creatorId) {
        RestaurantCommentEntity restaurantCommentEntity = restaurantCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cannot find restaurant comment with id:" + commentId));
        if(!restaurantCommentEntity.getCreator().equals(creatorId)){
            throw new LockedAccessException("cannot update restaurant comment with creator id " + creatorId);
        }
        restaurantCommentMapper.update(restaurantCommentEntity, update);
        RestaurantCommentEntity save = restaurantCommentRepository.save(restaurantCommentEntity);
        return restaurantCommentMapper.toDto(save);
    }

    @Override
    @Transactional
    public RestaurantCommentInfoDto create(RestaurantCommentCreateDto dto, UUID restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cant find restaurant by id: " + restaurantId));

        RestaurantCommentEntity entity = restaurantCommentMapper.toEntity(dto);
        restaurant.addComment(entity);
        RestaurantCommentEntity save = restaurantCommentRepository.saveAndFlush(entity);

        Double avgStar = restaurantCommentRepository.findAverageStarsByRestaurantId(restaurantId);
        restaurant.setAvgStar(avgStar);

        return restaurantCommentMapper.toDto(save);
    }

    @Override
    @Transactional
    public void delete(UUID commentId, UUID creatorId) {
        RestaurantCommentEntity restaurantCommentEntity = restaurantCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cannot find restaurant comment with id:" + commentId));
        if(!restaurantCommentEntity.getCreator().equals(creatorId)){
            throw new LockedAccessException("cannot update restaurant comment with creator id " + creatorId);
        }
        restaurantCommentRepository.deleteById(commentId);
        UUID restaurantId = restaurantCommentEntity.getRestaurant().getId();
        Double avgStar = restaurantCommentRepository.findAverageStarsByRestaurantId(restaurantId);
        restaurantRepository.updateAverageStars(restaurantId, avgStar);
    }

    @Override
    public List<RestaurantCommentInfoDto> getAllRestaurantComments(UUID restaurantId, Pageable pageable) {
        List<RestaurantCommentEntity> allByRestaurantId = restaurantCommentRepository
                .findAllByRestaurantId(restaurantId, pageable).getContent();
        return restaurantCommentMapper.toDtos(allByRestaurantId);
    }

    @Override
    public List<RestaurantCommentInfoDto> getAllCreatorComments(UUID creatorId, Pageable pageable) {
        List<RestaurantCommentEntity> allByCreatorId = restaurantCommentRepository
                .findAllByCreator(creatorId, pageable).getContent();
        return restaurantCommentMapper.toDtos(allByCreatorId);
    }
}
