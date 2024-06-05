package com.udemy.loans.repository;

import com.udemy.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 16:02
 * @Description :
 */
@Repository
public interface LoansRepository extends JpaRepository<Loans,Long> {

    Optional<Loans> findByMobileNumber(String mobileNumber);

    Optional<Loans> findByLoanNumber(String loanNumber);
}