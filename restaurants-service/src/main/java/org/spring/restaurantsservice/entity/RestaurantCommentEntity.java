package org.spring.restaurantsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "restaurant_comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantCommentEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "stars", nullable = false)
    private Double stars;
    @Column(name = "content")
    private String content;
    @Column(name = "creator_id")
    private UUID creator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;
    @CreationTimestamp
    private Instant created;
}
