package org.spring.ordersservice.service;

import org.spring.ordersservice.dto.request.order.OrderCreateDto;
import org.spring.ordersservice.dto.response.OrderInfoDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderInfoDto> readOrders(UUID userId, Pageable pageable);
    void delete(UUID orderId);
    OrderInfoDto create(OrderCreateDto dto);
}
