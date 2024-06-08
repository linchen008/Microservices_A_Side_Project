package com.udemy.loans;

import com.udemy.loans.dto.LoansContactInfoDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDTO.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Loans microservice REST API Documentation",
                description = "Lin's Bank Loans microservice REST API Documentation",
                version = "V1.0",
                contact = @Contact(
                        name = "Lin",
                        email = "totoro.douzi@gmail.com",
                        url = "https://github.com/linchenchn"
                )
        )
)
public class LoansApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }
}
