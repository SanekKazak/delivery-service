package org.spring.restaurantsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="restaurant_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantItemEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;
    @CreationTimestamp
    private Instant created;
    @UpdateTimestamp
    private Instant updated;
}
