package com.udemy.accounts.dto;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 17/06/2024 15:32
 * @param accountNumber
 * @param name
 * @param email
 * @param mobileNumber
 */

public record AccountMsgDTO(
        Long accountNumber,
        String name,
        String email,
        String mobileNumber) { }
