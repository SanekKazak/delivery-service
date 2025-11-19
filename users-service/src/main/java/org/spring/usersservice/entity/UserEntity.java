package org.spring.usersservice.entity;

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
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CardEntity> cards = new ArrayList<>();
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant created;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updated;

    public void addCard(CardEntity card) {
        this.cards.add(card);
        card.setUser(this);
    }

    public void removeCard(CardEntity card) {
        this.cards.remove(card);
        card.setUser(null);
    }
}
