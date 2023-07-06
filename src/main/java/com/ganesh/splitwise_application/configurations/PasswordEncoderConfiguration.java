package com.ganesh.splitwise_application.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {
    @Bean

    public SCryptPasswordEncoder sCryptEncoder(){
        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    @Bean
    @Primary
    public BCryptPasswordEncoder bCryptEncoder(){
        return new BCryptPasswordEncoder();
    }
}
