package com.fintech.AuthenficationJwt.services;

import com.fintech.AuthenficationJwt.entities.Role;
import com.fintech.AuthenficationJwt.entities.RoleEnum;

import java.util.Collection;

public interface RoleService {
    Role saveRole(Role role);

    Role getRole(Long id);

    Role getRole(RoleEnum roleEnum);

    Boolean deleteRole(Long id);

    Collection<Role> getRoles();
}
