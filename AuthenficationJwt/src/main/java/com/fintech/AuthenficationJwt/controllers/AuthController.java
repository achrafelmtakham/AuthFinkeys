package com.fintech.AuthenficationJwt.controllers;


import com.fintech.AuthenficationJwt.entities.User;
import com.fintech.AuthenficationJwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/v1")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/save-user")
    public User saveUser(@RequestBody User user) {
        // Enable user account
        user.setEnabled(true);
        // Encode user password before persisting
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        // Save user in database
        return this.userService.saveUser(user);
    }


}
