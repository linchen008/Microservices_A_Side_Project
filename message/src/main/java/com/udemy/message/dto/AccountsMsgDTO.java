package com.udemy.message.dto;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 16/06/2024 20:11
 * @param accountNumber
 * @param name
 * @param email
 * @param mobileNumber
 */

public record AccountsMsgDTO(Long accountNumber,
                             String name,
                             String email,
                             String mobileNumber) {
}
