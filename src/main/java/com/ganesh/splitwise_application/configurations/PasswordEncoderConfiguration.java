package com.ganesh.splitwise_application.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {
    @Bean
    public BCryptPasswordEncoder createBcryptEncoder(){
        return new BCryptPasswordEncoder();
    }
}
