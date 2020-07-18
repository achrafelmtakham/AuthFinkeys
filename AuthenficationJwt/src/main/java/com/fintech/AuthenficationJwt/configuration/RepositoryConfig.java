package com.fintech.AuthenficationJwt.configuration;

import com.fintech.AuthenficationJwt.entities.Role;
import com.fintech.AuthenficationJwt.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class, Role.class);
        config.getCorsRegistry().addMapping("/**")
                .allowedOrigins("http://localhost:4200");
    }
}
