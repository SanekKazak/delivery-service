package org.spring.authservice.dto.input.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {
    private List<OrderItemCreateDto> items;
    private UUID restaurant;
    private String restaurantName;
    private UUID buyer;
}
