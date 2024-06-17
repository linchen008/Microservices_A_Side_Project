package com.udemy.accounts.functions;

import com.udemy.accounts.service.IAccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 17/06/2024 15:27
 * @Description :
 */
@Configuration
@Slf4j
public class AccountsFunctions {

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService accountsService) {
        return accountNumber ->{
            log.info("===========Updating account numbe===========: {}", accountNumber.toString());
            accountsService.updateCommunicationStatus(accountNumber);
        };
    }
}
