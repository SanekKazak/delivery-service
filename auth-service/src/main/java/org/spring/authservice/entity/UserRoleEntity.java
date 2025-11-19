package org.spring.authservice.entity;

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
@Table(name = "user_roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoleEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "name", unique = true)
    private String name;
    @ManyToMany(
            mappedBy = "roles"
    )
    private List<UserDetailsEntity> users = new ArrayList<>();
    @CreationTimestamp
    private Instant created;
    @UpdateTimestamp
    private Instant updated;

    public UserRoleEntity(String name) {
        this.name = name;
    }
}
