package org.spring.usersservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "token", nullable = false)
    private UUID token;
    @Column(name = "pan_end")
    private String panEnd;
    @Column(name = "card_supplier")
    private String cardSupplier;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
