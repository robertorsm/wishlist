package com.br.wishlist.service;

import com.br.wishlist.exception.TooManyRequestsException;
import com.br.wishlist.model.Wish;
import com.br.wishlist.records.WishRecord;
import com.br.wishlist.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishService {
    private static final Integer MAX_LIMIT = 20;


    private final WishRepository wishRepository;


    public List<WishRecord> getAllWishesByCustomerId(Long customerId) {
        return wishRepository.findAllByCustomerId(customerId).stream().map(Wish::documentToRecord).toList();
    }

    public void createWish(WishRecord wish) {
        Integer count = wishRepository.countByCustomerId(wish.customerId());
        if(count<=MAX_LIMIT){
            wishRepository.save(wish.toDocument());
        }else {
            throw new TooManyRequestsException("Customer has reach the max limit of items in wish list!");
        }

    }

    public void deleteWish(Long productId, Long customerId) {
        wishRepository.deleteByProductIdAndCustomerId(productId, customerId);
    }

    public Boolean existsByProductIdAndCustomerId(Long productId, Long customerId) {
        return wishRepository.existsByProductIdAndCustomerId(productId, customerId);
    }

}
