package com.ganesh.splitwise_application.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class CustomBeans {
    @Bean("bcrypt")
    BCryptPasswordEncoder getBcryptEncoder(){
        return new BCryptPasswordEncoder(4);
    }
    @Bean("scrypt")
    SCryptPasswordEncoder getScryptEncoder(){
        return new SCryptPasswordEncoder(4,4,2,4,4);
    }

    @Bean("modelMapper")
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
