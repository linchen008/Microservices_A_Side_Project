package com.udemy.loans.mapper;

import com.udemy.loans.dto.LoansDTO;
import com.udemy.loans.entity.Loans;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 19:38
 * @Description :
 */
public class LoansMapper {
    public static LoansDTO mapToLoansDTO(Loans loans, LoansDTO loansDTO) {
        loansDTO.setLoanNumber(loans.getLoanNumber());
        loansDTO.setLoanType(loans.getLoanType());
        loansDTO.setMobileNumber(loans.getMobileNumber());
        loansDTO.setTotalLoan(loans.getTotalLoan());
        loansDTO.setAmountPaid(loans.getAmountPaid());
        loansDTO.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDTO;
    }

    public static Loans mapToLoans(LoansDTO loansDTO, Loans loans) {
        loans.setLoanNumber(loansDTO.getLoanNumber());
        loans.setLoanType(loansDTO.getLoanType());
        loans.setMobileNumber(loansDTO.getMobileNumber());
        loans.setTotalLoan(loansDTO.getTotalLoan());
        loans.setAmountPaid(loansDTO.getAmountPaid());
        loans.setOutstandingAmount(loansDTO.getOutstandingAmount());
        return loans;
    }
}