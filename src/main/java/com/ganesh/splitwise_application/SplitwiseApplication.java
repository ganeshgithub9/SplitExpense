package com.ganesh.splitwise_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.ganesh.splitwise_application.services"})
@ComponentScan(basePackages = {"com.ganesh.splitwise_application.repositories"})
@ComponentScan(basePackages = {"com.ganesh.splitwise_application.controllers"})
@EnableJpaAuditing
public class SplitwiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

}
