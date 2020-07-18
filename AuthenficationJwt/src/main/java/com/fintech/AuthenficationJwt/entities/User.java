package com.fintech.AuthenficationJwt.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotEmpty(message = "username must not be null")
    private String username;

    @Column(name = "email", unique = true, insertable = true, updatable = true, nullable = false, length = 255)
    @NotEmpty(message = "email must not be null")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "password must not be null")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public void addRole(Role role) {
        if( this.roles == null ) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }


}
