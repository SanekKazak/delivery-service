package org.spring.authservice.client.order;

import org.spring.authservice.dto.input.order.OrderCreateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@FeignClient(name = "order-client", url = "http://localhost:8081/api/orders")
public interface OrderClient {
    @PostMapping
    String create(@RequestBody OrderCreateDto dto);

    @DeleteMapping("/{orderId}")
    void delete(@PathVariable("orderId") UUID orderId);

    @GetMapping("/{userId}")
    String read(@PathVariable("userId") UUID userId, @PageableDefault(size = 10, sort = "created") Pageable pageable);
}
