package com.udemy.accounts.service.impl;

import com.udemy.accounts.dto.AccountsDTO;
import com.udemy.accounts.dto.CardsDTO;
import com.udemy.accounts.dto.CustomerDetailsDTO;
import com.udemy.accounts.dto.LoansDTO;
import com.udemy.accounts.entity.Accounts;
import com.udemy.accounts.entity.Customer;
import com.udemy.accounts.exception.ResourceNotFoundException;
import com.udemy.accounts.mapper.AccountsMapper;
import com.udemy.accounts.mapper.CustomerMapper;
import com.udemy.accounts.repository.AccountsRepository;
import com.udemy.accounts.repository.CustomerRepository;
import com.udemy.accounts.service.ICustomerService;
import com.udemy.accounts.service.client.CardsFeignClient;
import com.udemy.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 10/06/2024 22:14
 * @Description :
 */
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Qualifier("com.udemy.accounts.service.client.CardsFeignClient")
    private CardsFeignClient cardsFeignClient;
    @Qualifier("com.udemy.accounts.service.client.LoansFeignClient")
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber
     * @param correlationId - Correlation ID value generated at Edge server
     * @return
     */
    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber,String correlationId) {
        //TODO: 1.find the customer info
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        //TODO: 2.find the Account info based on the customer info
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

        //TODO: 3.wave the customer info into customerDetailsDTO
        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDTO(customer,new CustomerDetailsDTO());

        //TODO: 4.wave the accountDTO into customerDetailsDTO
        customerDetailsDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts,new AccountsDTO()));

        //--------------OpenFeign + Load Balancer----------------------------------------------------------
        //TODO: 5.wave the CardDTO details into customerDetailsDTO
        //5.1 invoke OpenFeign API provided by Loans Microservice
        ResponseEntity<LoansDTO> loansDTOResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        //5.2 wave the LoanDTO details to customerDTO
        if (null != loansDTOResponseEntity) {
            customerDetailsDTO.setLoansDTO(loansDTOResponseEntity.getBody());
        }

        //TODO: 6.wave the cardDTO into customerDetailsDTO
        //6.1 invoke OpenFeign API provided by Cards Microservice
        ResponseEntity<CardsDTO> cardsDTOResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        //6.2 wave the CardDTO into customerDetailsDTO
        if (null != cardsDTOResponseEntity) {
            customerDetailsDTO.setCardsDTO(cardsDTOResponseEntity.getBody());
        }
        //--------------OpenFeign + Load Balancer----------------------------------------------------------

        return customerDetailsDTO;
    }
}
