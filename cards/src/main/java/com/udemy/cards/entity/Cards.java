package com.udemy.cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 21:16
 * @Description :
 */
@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cards extends BaseEntity {

//  `mobile_number` varchar(15) NOT NULL,
//  `card_number` varchar(100) NOT NULL,
//  `card_type` varchar(100) NOT NULL,
//  `total_limit` int NOT NULL,
//  `amount_used` int NOT NULL,
//  `available_amount` int NOT NULL

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardsId;

    private String mobileNumber;

    private String cardNumber;

    private String cardType;

    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
}
