package com.udemy.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 08/06/2024 14:32
 * @Description :
 */

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDTO(String message,
                                     Map<String, String> contactDetails,
                                     List<String> onCallSupport) {
}
