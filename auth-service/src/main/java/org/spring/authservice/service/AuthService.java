package org.spring.authservice.service;

import org.spring.authservice.dto.input.auth.UserCredentialsDto;
import org.spring.authservice.dto.input.order.OrderCreateDto;
import org.spring.authservice.dto.input.restaurants.ProviderOrderDetailsDto;
import org.spring.authservice.dto.output.UserDetailsDto;

import java.util.UUID;

public interface AuthService {
    UserDetailsDto register(UserCredentialsDto dto);
    UserDetailsDto setRestaurantManager(UUID restaurantId, UUID userId);
    void delete(UUID userId);
    String orderCreation(ProviderOrderDetailsDto dto);
}
