package org.spring.authservice.client.user;

import org.spring.authservice.dto.input.user.UserCreateDto;
import org.spring.authservice.dto.input.user.UserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "user-client", url = "http://localhost:8080/api/users")
public interface UserClient {
    @GetMapping("/{userId}")
    String find(@PathVariable("userId") UUID id);

    @DeleteMapping("/{userId}")
    void delete(@PathVariable("userId") UUID id);

    @PostMapping
    UserInfoDto register(@RequestBody UserCreateDto dto);
}
