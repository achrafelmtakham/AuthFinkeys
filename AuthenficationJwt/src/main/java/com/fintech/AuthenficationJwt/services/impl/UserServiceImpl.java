package com.fintech.AuthenficationJwt.services.impl;

import com.fintech.AuthenficationJwt.dao.UserRepository;
import com.fintech.AuthenficationJwt.entities.User;
import com.fintech.AuthenficationJwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        // Save user in the database using dao repository
        return this.userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUser(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Boolean deleteUser(Long id) {
        this.userRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<User> getUsers() {
        return this.userRepository.findAll();
    }
}
