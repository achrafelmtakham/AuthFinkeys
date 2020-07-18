package com.fintech.AuthenficationJwt.services.impl;

import com.fintech.AuthenficationJwt.dao.RoleRepository;
import com.fintech.AuthenficationJwt.entities.Role;
import com.fintech.AuthenficationJwt.entities.RoleEnum;
import com.fintech.AuthenficationJwt.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Role getRole(Long id) {
        return this.roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role getRole(RoleEnum roleEnum) {
        return this.roleRepository.findByRoleName(roleEnum).orElse(null);
    }

    @Override
    public Boolean deleteRole(Long id) {
        this.roleRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<Role> getRoles() {
        return this.roleRepository.findAll();
    }
}
