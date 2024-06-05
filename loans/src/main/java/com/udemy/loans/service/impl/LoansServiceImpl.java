package com.udemy.loans.service.impl;

import com.udemy.loans.constants.LoansConstants;
import com.udemy.loans.dto.LoansDTO;
import com.udemy.loans.entity.Loans;
import com.udemy.loans.exception.LoanAlreadyExistsException;
import com.udemy.loans.exception.ResourceNotFoundException;
import com.udemy.loans.mapper.LoansMapper;
import com.udemy.loans.repository.LoansRepository;
import com.udemy.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 15:26
 * @Description :
 */
@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        //TODO: check the loan, if existed, not create, throw a error(msg:LoanAlreadyExistsException)
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already exists with mobile number " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * helper fun for creating new loan
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        //generate a loan number
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public LoansDTO fetchLoan(String mobileNumber) {
       Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
        );
        return LoansMapper.mapToLoansDTO(loans,new LoansDTO());
    }

    /**
     * @param loansDTO
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDTO loansDTO) {
        Loans loans = loansRepository.findByLoanNumber(loansDTO.getLoanNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Loan","loans",loansDTO.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDTO,loans);
        loansRepository.save(loans);
        return true;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
       Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
        );
       loansRepository.deleteById(loans.getLoanId());
       return true;
    }
}