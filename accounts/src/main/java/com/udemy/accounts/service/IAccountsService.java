package com.udemy.accounts.service;

import com.udemy.accounts.dto.CustomerDTO;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 02/06/2024 22:03
 * @Description :
 */

public interface IAccountsService {

    /**
     *
     * @param customerDTO
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber
     * @return Accounts Details based on a given mobileNumber
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDTO
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);
}
