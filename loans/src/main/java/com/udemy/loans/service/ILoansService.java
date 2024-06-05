package com.udemy.loans.service;

import com.udemy.loans.dto.LoansDTO;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 15:23
 * @Description :
 */
public interface ILoansService {

    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber
     * @return
     */
    LoansDTO fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDTO
     * @return
     */
    boolean updateLoan(LoansDTO loansDTO);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteLoan(String mobileNumber);
}
