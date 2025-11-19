package org.spring.ordersservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.spring.ordersservice.dto.request.order.OrderCreateDto;
import org.spring.ordersservice.dto.response.OrderInfoDto;
import org.spring.ordersservice.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderCrudController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderInfoDto> create(
            @RequestBody @Valid OrderCreateDto dto
    ){
        return ResponseEntity.ok(orderService.create(dto));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> delete(
            @PathVariable("orderId") @NotNull(message = "order Id is null") UUID orderId
    ){
        orderService.delete(orderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderInfoDto>> read(
            @PathVariable("userId") @NotNull(message = "order Id is null") UUID userId,
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    ){
        return ResponseEntity.ok(orderService.readOrders(userId, pageable));
    }
}
