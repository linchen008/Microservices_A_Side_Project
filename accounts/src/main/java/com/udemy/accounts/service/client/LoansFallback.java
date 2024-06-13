package com.udemy.accounts.service.client;

import com.udemy.accounts.dto.LoansDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 13/06/2024 20:50
 * @Description :
 */
@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<LoansDTO> fetchLoanDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
