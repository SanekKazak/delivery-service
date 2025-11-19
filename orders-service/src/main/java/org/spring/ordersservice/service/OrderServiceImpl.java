package org.spring.ordersservice.service;

import lombok.RequiredArgsConstructor;
import org.spring.ordersservice.dto.request.order.OrderCreateDto;
import org.spring.ordersservice.dto.request.order.OrderItemCreateDto;
import org.spring.ordersservice.dto.response.OrderInfoDto;
import org.spring.ordersservice.entity.OrderEntity;
import org.spring.ordersservice.mapper.OrderMapper;
import org.spring.ordersservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderInfoDto> readOrders(UUID userId, Pageable pageable) {
        List<OrderEntity> userOrders = repository.findByBuyer(userId, pageable).getContent();
        return orderMapper.fromEntitiesToInfoDtos(userOrders);
    }

    @Override
    public void delete(UUID orderId) {
        repository.deleteById(orderId);
    }

    @Override
    public OrderInfoDto create(OrderCreateDto dto) {
        OrderEntity orderEntity = orderMapper.fromCreateToEntity(dto);
        List<OrderItemCreateDto> items = dto.items();
        if(items!=null && !items.isEmpty()){
            double sum = items.stream()
                    .mapToDouble(o->o.price()*o.quantity())
                    .sum();
            orderEntity.setTotalPrice(sum);
        }
        OrderEntity save = repository.saveAndFlush(orderEntity);
        return orderMapper.fromEntityToInfoDto(save);
    }
}
