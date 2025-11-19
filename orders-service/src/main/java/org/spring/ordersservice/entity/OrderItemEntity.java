package org.spring.ordersservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "restaurant_item_id", nullable = false)
    private UUID restaurantItem;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
    @Column(name ="quantity", nullable = false)
    private Integer quantity;
    @Column(name ="price", nullable = false)
    private Double price;
    @Column(name = "name")
    private String name;
}
