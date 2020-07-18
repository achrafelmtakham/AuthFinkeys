package com.fintech.AuthenficationJwt.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    private RoleEnum roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Collection<User> users;
}
