package com.fintech.AuthenficationJwt.services.impl;

import com.fintech.AuthenficationJwt.entities.User;
import com.fintech.AuthenficationJwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = this.userService.getUser(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user != null) {
            user.getRoles().forEach(role -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
            });
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("No user found with " + email);
    }
}
