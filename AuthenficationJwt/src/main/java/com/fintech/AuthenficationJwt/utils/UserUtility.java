package com.fintech.AuthenficationJwt.utils;

import com.fintech.AuthenficationJwt.entities.User;
import com.fintech.AuthenficationJwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtility {
    @Autowired
    private UserService userService;

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = authentication.getName();
            return this.userService.getUser(email);
        }
        return null;
    }
}
