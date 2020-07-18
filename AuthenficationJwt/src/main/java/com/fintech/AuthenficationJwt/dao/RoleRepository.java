package com.fintech.AuthenficationJwt.dao;

import com.fintech.AuthenficationJwt.entities.Role;
import com.fintech.AuthenficationJwt.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleEnum roleEnum);
}
