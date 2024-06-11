package com.udemy.accounts;

import com.udemy.accounts.dto.AccountsContactInfoDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDTO.class})
@OpenAPIDefinition(info = @Info(
        title = "Accounts microservice REST API Documentation",
        description = "Lin's Bank Accounts microoservice REST API Docu.",
        contact = @Contact(
                name = "Lin",
                email = "totoro.douzi@gmail.com",
                url = "https://lin.com"
        ),
        license = @License(
                name = "Apache 2.0",
                url = "https://lin.com"
        ),
        version = "1.0"
))
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
