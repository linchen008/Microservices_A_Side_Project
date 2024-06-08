package com.udemy.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 08/06/2024 15:21
 * @Description :
 */
@ConfigurationProperties(prefix = "loans")
public record LoansContactInfoDTO(String message,
                                  Map<String,String> contactDetails,
                                  List<String> onCallSupport) {
}
