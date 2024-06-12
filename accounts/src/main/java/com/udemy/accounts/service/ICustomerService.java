package com.udemy.accounts.service;

import com.udemy.accounts.dto.CustomerDetailsDTO;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 10/06/2024 22:14
 * @Description :
 */
public interface ICustomerService {

    /**
     *
     * @param mobileNumber
     @param correlationId - Correlation ID value generated at Edge server
     @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId);
}
