package com.fintech.AuthenficationJwt.services;

import com.fintech.AuthenficationJwt.entities.User;

import java.util.Collection;

public interface UserService {
    User saveUser(User user);

    User getUser(Long id);

    User getUser(String email);

    Boolean deleteUser(Long id);

    Collection<User> getUsers();
}
