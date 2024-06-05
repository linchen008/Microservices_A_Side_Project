package com.udemy.cards.repository;

import com.udemy.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 21:44
 * @Description :
 */
@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {

    Optional<Cards> findByMobileNumber(String mobileNumber);

    Optional<Cards> findByCardNumber(String cardNumber);

}
