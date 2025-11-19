package org.spring.usersservice.service.user;

import lombok.RequiredArgsConstructor;
import org.spring.usersservice.dto.request.UserCreateDto;
import org.spring.usersservice.dto.response.UserInfoDto;
import org.spring.usersservice.entity.UserEntity;
import org.spring.usersservice.exception.NotFoundEntityByIdException;
import org.spring.usersservice.mapper.UserMapper;
import org.spring.usersservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    @Transactional
    public UserInfoDto create(UserCreateDto dto) {
        UserEntity entity = mapper.fromRegisterToEntity(dto);
        UserEntity save = userRepository.save(entity);
        return mapper.fromEntityToInfoDto(save);
    }

    @Override
    public List<UserInfoDto> readAll() {
        List<UserEntity> allUsers = userRepository.findAll();
        return mapper.fromEntitiesToInfoDtos(allUsers);
    }

    @Override
    public UserInfoDto readUser(UUID id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundEntityByIdException("Cant find user with id " + id));
        return mapper.fromEntityToInfoDto(userEntity);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
