package org.spring.usersservice.service.user;

import org.spring.usersservice.dto.request.UserCreateDto;
import org.spring.usersservice.dto.response.UserInfoDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserInfoDto create(UserCreateDto dto);
    List<UserInfoDto> readAll();
    UserInfoDto readUser(UUID id);
    void delete(UUID id);
}
