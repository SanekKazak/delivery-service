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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "owner_id")
    private UUID ownerId;
    @OneToMany(
            mappedBy = "restaurant",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<RestaurantItemEntity> items = new ArrayList<>();
    @OneToMany(
            mappedBy = "restaurant",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<RestaurantCommentEntity> comments = new ArrayList<>();
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;
    @Column(name = "description")
    private String description;
    @Column(name = "avg_star")
    private Double avgStar;
    @CreationTimestamp
    private Instant created;
    @UpdateTimestamp
    private Instant updated;

    public void setItems(List<RestaurantItemEntity> items) {
        this.items = items;
        items.forEach(item -> item.setRestaurant(this));
    }

    public void addItem(RestaurantItemEntity item) {
        this.items.add(item);
        item.setRestaurant(this);
    }

    public void addComment(RestaurantCommentEntity comment) {
        this.comments.add(comment);
        comment.setRestaurant(this);
    }
}
