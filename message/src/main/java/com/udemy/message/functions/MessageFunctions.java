package com.udemy.message.functions;

import com.udemy.message.dto.AccountsMsgDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 16/06/2024 20:27
 * @Description :
 */

@Configuration
@Slf4j
public class MessageFunctions {
    @Bean
    public Function<AccountsMsgDTO, AccountsMsgDTO> email() {
        return accountsMsgDTO -> {
            log.info("===========Sending email with the details===========: {}", accountsMsgDTO.toString());
            return accountsMsgDTO;
        };
    }

    @Bean
    public Function<AccountsMsgDTO, Long> sms() {
        return accountsMsgDTO -> {
            log.info("===========Sending sms with the details===========: {}", accountsMsgDTO.toString());
            return accountsMsgDTO.accountNumber();
        };
    }
}
