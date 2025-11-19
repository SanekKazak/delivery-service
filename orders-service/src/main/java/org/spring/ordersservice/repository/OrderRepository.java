package org.spring.ordersservice.repository;

import org.spring.ordersservice.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    Page<OrderEntity> findByBuyer(UUID buyer, Pageable pageable);
}
