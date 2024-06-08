package com.udemy.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 08/06/2024 14:56
 * @Description :
 */

@ConfigurationProperties(prefix = "cards")
public record CardsContactInfoDTO(String message,
                                  Map<String,String> contactDetails,
                                  List<String> onCallSupport) {
}
