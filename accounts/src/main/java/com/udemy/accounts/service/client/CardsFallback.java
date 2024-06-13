package com.udemy.accounts.service.client;

import com.udemy.accounts.dto.CardsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 13/06/2024 20:51
 * @Description :
 */
@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardsDTO> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
