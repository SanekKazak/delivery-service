package org.spring.authservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailsEntity{
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "referenceId", unique = true)
    private UUID referenceId;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserDetailsEntity(String login, String password, UUID id) {
        this.login = login;
        this.password = password;
        this.referenceId = id;
    }
    public void addRole(UserRoleEntity userRoleEntity) {
        roles.add(userRoleEntity);
        userRoleEntity.getUsers().add(this);
    }
}
