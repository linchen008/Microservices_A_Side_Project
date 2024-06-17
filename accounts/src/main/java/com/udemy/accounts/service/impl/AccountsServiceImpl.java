package com.udemy.accounts.service.impl;

import com.udemy.accounts.constants.AccountsConstants;
import com.udemy.accounts.dto.AccountMsgDTO;
import com.udemy.accounts.dto.AccountsDTO;
import com.udemy.accounts.dto.CustomerDTO;
import com.udemy.accounts.entity.Accounts;
import com.udemy.accounts.entity.Customer;
import com.udemy.accounts.exception.CustomerAlreadyExistsException;
import com.udemy.accounts.exception.ResourceNotFoundException;
import com.udemy.accounts.mapper.AccountsMapper;
import com.udemy.accounts.mapper.CustomerMapper;
import com.udemy.accounts.repository.AccountsRepository;
import com.udemy.accounts.repository.CustomerRepository;
import com.udemy.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 02/06/2024 22:37
 * @Description :
 */
@Service
@AllArgsConstructor //constructor for @Autowire
@Slf4j
public class AccountsServiceImpl implements IAccountsService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;
    private StreamBridge streamBridge;

    /**
     * @param customerDTO
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());

        //TODO:check the mobileNumber if not used in a exist account
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            //TODO: throw a exception
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    + customerDTO.getMobileNumber());
        }
        //TODO: AuditorAware automatically populate the fields(Update/createdAt/By)
        //customer.setCreatedAt(LocalDateTime.now());
        //customer.setCreatedBy("Lin");

        //save the customer info to database
        Customer savedCustomer = customerRepository.save(customer);
        //save the account info to database
        Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));
        sendMessage(savedAccount,savedCustomer);
    }

    //TODO: send msg to Msg Microservice via MQ
    private void sendMessage(Accounts accounts, Customer customer) {
        AccountMsgDTO accountMsgDTO = new AccountMsgDTO(accounts.getAccountNumber(),
                                                        customer.getName(),
                                                        customer.getEmail(),
                                                        customer.getMobileNumber());
        log.info("===========Sending account message to Msg MQ, Msg===========: {}", accountMsgDTO);
        boolean send = streamBridge.send("sendCommunication-out-0", accountMsgDTO);
        log.info("===========Is the Communication request successfully triggered ? ===========: {}", send);
    }

    /**
     * helper fun for creating a new account with given customer info
     *
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());

        //create a accountNumber with randomize fun
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccountNumber);

        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        //TODO: AuditorAware automatically populate the fields(Update/createdAt/By)
//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("Lin");
        return newAccount;
    }

    /**
     * @param mobileNumber Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        //TODO: 1.find the customer with mobile number
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                //TODO: throw a exception that can not find a account with the number
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        //TODO: 2.get the account details with customer ID
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        //TODO: 3.convert raw data from database to DTO
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());

        //TODO: 4.wave the accountDTO data to customerDTO
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));
        return customerDTO;
    }

    /**
     * @param customerDTO
     * @return boolean indicating if the update of account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        //TODO: check if the request info has Account data
        if (accountsDTO != null) {
            Accounts accounts = accountsRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDTO.getAccountNumber().toString())
            );
            //save the new account details to database
            AccountsMapper.mapToAccounts(accountsDTO, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber
     * @return boolean indicating if the delete of account & customer is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        //find the customer info by mobile number
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        //delete the account & customer by customer ID
        //deleteByCustomerId: open transaction and modifying annotation
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
     * @param accountNumber - Long
     * @return boolean indicating if the update of communication status is successful or not
     */
    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated = false;
        if (accountNumber != null) {
            Accounts accounts = accountsRepository.findById(accountNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString())
            );
            accounts.setCommunicationSw(true);
            accountsRepository.save(accounts);
            isUpdated = true;
        }
        return isUpdated;
    }
}