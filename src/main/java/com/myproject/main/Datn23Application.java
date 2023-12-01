package com.myproject.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.myproject.main.config.SecurityConfig;


@SpringBootApplication
@EntityScan("com.myproject.main.model")
@EnableJpaRepositories(basePackages = "com.myproject.main.repository")
@Import(SecurityConfig.class)
public class Datn23Application {
    public static void main(String[] args) {
        SpringApplication.run(Datn23Application.class, args);
    }
}