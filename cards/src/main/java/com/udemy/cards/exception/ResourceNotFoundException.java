package com.udemy.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 22:12
 * @Description :
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'",
                resourceName, fieldName, fieldValue));
    }
}
