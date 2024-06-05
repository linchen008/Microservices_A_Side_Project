package com.udemy.accounts.repository;

import com.udemy.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 02/06/2024 22:48
 * @Description :
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //TODO: check the mobile number if existed
    Optional<Customer> findByMobileNumber(String mobileNumber);

}
