package com.udemy.cards.service.impl;

import com.udemy.cards.constants.CardsConstants;
import com.udemy.cards.dto.CardsDTO;
import com.udemy.cards.entity.Cards;
import com.udemy.cards.exception.CardAlreadyExistsException;
import com.udemy.cards.exception.ResourceNotFoundException;
import com.udemy.cards.mapper.CardsMapper;
import com.udemy.cards.repository.CardRepository;
import com.udemy.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 21:41
 * @Description :
 */

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardRepository cardRepository;


    /**
     * @param mobileNumber
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException(mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    /**
     * helper fun for creating new card
     *
     * @param mobileNumber
     * @return
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCards = new Cards();

        //generate card number
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCards.setCardNumber(Long.toString(randomCardNumber));

        newCards.setMobileNumber(mobileNumber);
        newCards.setCardType(CardsConstants.CREDIT_CARD);
        newCards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCards.setAmountUsed(0);
        newCards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return newCards;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public CardsDTO fetchCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Card","MobileNumber",mobileNumber)
        );
        return CardsMapper.mapToCardsDTO(cards,new CardsDTO());
    }

    /**
     * @param cardsDTO
     * @return
     */
    @Override
    public boolean updateCard(CardsDTO cardsDTO) {
        Cards cards = cardRepository.findByCardNumber(cardsDTO.getCardNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Card","CardNumber",cardsDTO.getCardNumber())
        );
        //receive CardsDTO,
        // convert it to cards raw data,
        // update the exist one,
        // then save to database
        CardsMapper.mapToCards(cardsDTO,cards);
        cardRepository.save(cards);
        return true;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Card","MobileNumber",mobileNumber)
        );
        cardRepository.deleteById(cards.getCardsId());
        return true;
    }
}
