package com.udemy.accounts.mapper;

import com.udemy.accounts.dto.CustomerDTO;
import com.udemy.accounts.entity.Customer;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 03/06/2024 14:43
 * @Description :
 */
public class CustomerMapper {
    public static CustomerDTO mapToCustomerDTO(Customer customer,CustomerDTO customerDTO) {
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
