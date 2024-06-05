package com.udemy.cards;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Cards microservice REST API Documentation",
                description = "Lin's Bank Cards microservice REST API Documentation",
                version = "1.0",
                contact = @Contact(
                        name = "Lin",
                        email = "totoro.douzi@gmail.com",
                        url = "https://github.com/linchenchn"
                )
        )
)
public class CardsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }
}
