package com.udemy.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 22:03
 * @Description :
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException(String message) {
        super(message);
    }
}
