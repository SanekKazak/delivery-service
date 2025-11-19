package org.spring.authservice.service;

import lombok.RequiredArgsConstructor;
import org.spring.authservice.client.order.OrderClient;
import org.spring.authservice.client.restaurants.RestaurantClient;
import org.spring.authservice.client.user.UserClient;
import org.spring.authservice.dto.input.auth.UserCredentialsDto;
import org.spring.authservice.dto.input.order.OrderCreateDto;
import org.spring.authservice.dto.input.restaurants.ProviderOrderDetailsDto;
import org.spring.authservice.dto.input.user.UserCreateDto;
import org.spring.authservice.dto.input.user.UserInfoDto;
import org.spring.authservice.dto.output.*;
import org.spring.authservice.entity.UserDetailsEntity;
import org.spring.authservice.entity.UserRoleEntity;
import org.spring.authservice.exception.NotFoundEntityByIdException;
import org.spring.authservice.mapper.UserDetailsMapper;
import org.spring.authservice.repository.UserDetailsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsMapper mapper;
    private final UserClient userClient;
    private final RestaurantClient restaurantClient;
    private final OrderClient orderClient;
    private final UserDetailsRepository repository;

    @Override
    public String orderCreation(ProviderOrderDetailsDto dto) {
        OrderCreateDto details = restaurantClient.getDetails(dto);
        details.setBuyer(UUID.randomUUID());
        return orderClient.create(details);
    }

    @Override
    public UserDetailsDto setRestaurantManager(UUID restaurantId, UUID userId) {
        UserDetailsEntity userDetailsEntity = repository.findByReferenceId(userId)
                .orElseThrow(() -> new NotFoundEntityByIdException("cant find user with id: " + userId));
        String rawRole = "MANAGER_" + restaurantId;
        UserRoleEntity role = new UserRoleEntity(rawRole);
        userDetailsEntity.getRoles().add(role);
        UserDetailsEntity save = repository.save(userDetailsEntity);
        return mapper.fromEntityToDto(save);
    }

    @Override
    @Transactional
    public void delete(UUID userId) {
        userClient.delete(userId);
        repository.deleteByReferenceId(userId);
    }

    @Override
    @Transactional
    public UserDetailsDto register(UserCredentialsDto dto) {
        boolean existence = repository.existsByLogin(dto.login());
        if(existence){
            throw new NotFoundEntityByIdException("cant find user by login: " +  dto.login());
        }
        UserCreateDto userCreateDto = mapper.fromCredentialsToCreateDto(dto);
        UserInfoDto register = userClient.register(userCreateDto);

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity(
                dto.login(),
                passwordEncoder.encode(dto.password()),
                register.id()
                );
        UserDetailsEntity save = repository.save(userDetailsEntity);

        return mapper.fromEntityToDto(save);
    }
}
