package com.br.wishlist.application.services;

import com.br.wishlist.application.exception.TooManyRequestsException;
import com.br.wishlist.application.gateways.WishGateway;
import com.br.wishlist.application.usecases.WishUseCase;
import com.br.wishlist.domain.entity.Wish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishUseCase {

    private static final Integer MAX_LIMIT = 20;

    private final WishGateway wishGateway;

    public Wish createWish(Wish wish) {
        Integer count = wishGateway.countByCustomerId(wish.customerId());
        if(count<MAX_LIMIT){
            return wishGateway.createWish(wish);
        }else {
            throw new TooManyRequestsException("Customer has reach the max limit of items in wish list!");
        }
    }

    @Override
    public List<Wish> getAllWishesByCustomerId(Long customerId) {
        return wishGateway.getAllWishesByCustomerId(customerId);
    }

    @Override
    public void deleteWishByProductIdAndCustomerId(Long productId, Long customerId) {
        wishGateway.deleteWish(productId, customerId);
    }

    @Override
    public Boolean existsByProductIdAndCustomerId(Long productId, Long customerId) {
        return wishGateway.existsWishByProductIdAndCustomerId(productId, customerId);
    }
}
