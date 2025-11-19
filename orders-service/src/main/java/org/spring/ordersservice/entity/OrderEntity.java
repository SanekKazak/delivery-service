package org.spring.ordersservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurant;
    @Column(name = "user_id", nullable = false)
    private UUID buyer;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    @Column(name = "restaurant_name")
    private String restaurantName;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant created;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updated;
    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItemEntity> items = new ArrayList<>();

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
        items.forEach(item -> item.setOrder(this));
    }
}
