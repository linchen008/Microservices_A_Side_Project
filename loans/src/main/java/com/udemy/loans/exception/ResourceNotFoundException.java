package com.udemy.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 16:10
 * @Description :
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("Resource %s not found for field %s with given value %s", resourceName, fieldName, fieldValue));
    }
}