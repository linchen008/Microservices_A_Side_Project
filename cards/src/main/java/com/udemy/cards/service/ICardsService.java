package com.udemy.cards.service;

import com.udemy.cards.dto.CardsDTO;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 21:14
 * @Description :
 */

public interface ICardsService {

    /**
     *
     * @param mobileNumber
     */
    void createCard(String mobileNumber);

    /**
     *
     * @param mobileNumber
     * @return
     */
    CardsDTO fetchCard(String mobileNumber);

    /**
     *
     * @param cardsDTO
     * @return
     */
    boolean updateCard(CardsDTO cardsDTO);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteCard(String mobileNumber);
}
