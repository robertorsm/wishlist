package com.br.wishlist.application.services;

import com.br.wishlist.application.exception.MaxWishLimitReachedException;
import com.br.wishlist.application.exception.WishNotFoundException;
import com.br.wishlist.application.gateways.WishGateway;
import com.br.wishlist.application.usecases.WishUseCase;
import com.br.wishlist.domain.entity.Wish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
            log.error("Customer {} has reached the max limit of items in wish list!", wish.customerId());
            throw new MaxWishLimitReachedException("Customer has reached the max limit of items in wish list!");
        }
    }

    @Override
    public List<Wish> getAllWishesByCustomerId(Long customerId,Integer page, Integer size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        List<Wish> wishes = wishGateway.getAllWishesByCustomerId(customerId,pageable);
        if (wishes.isEmpty()) {
            log.error("No wishes found for customer with ID {}", customerId);
            throw new WishNotFoundException("No wishes found for customer with ID " + customerId);
        }
        return wishes;

    }

    @Override
    public void deleteWishByProductIdAndCustomerId(Long productId, Long customerId) {
        if (!wishGateway.existsWishByProductIdAndCustomerId(productId, customerId)) {
            log.error("Wish not found for product ID {} and customer ID {}", productId, customerId);
            throw new WishNotFoundException("Wish not found for product ID " + productId + " and customer ID " + customerId);
        }
        wishGateway.deleteWish(productId, customerId);
    }

    @Override
    public Boolean existsByProductIdAndCustomerId(Long productId, Long customerId) {
        return wishGateway.existsWishByProductIdAndCustomerId(productId, customerId);
    }
}
