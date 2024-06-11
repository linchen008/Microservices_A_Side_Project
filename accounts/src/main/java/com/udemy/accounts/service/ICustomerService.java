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
     * @return
     */
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
