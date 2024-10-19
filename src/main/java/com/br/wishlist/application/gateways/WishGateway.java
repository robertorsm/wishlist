package com.br.wishlist.application.gateways;

import com.br.wishlist.domain.entity.Wish;

import java.util.List;

public interface WishGateway {
    List<Wish> getAllWishesByCustomerId(Long customerId);
    Wish createWish(Wish wish);
    void deleteWish(Long productId, Long customerId);
    Boolean existsWishByProductIdAndCustomerId (Long productId, Long customerId);
    Integer countByCustomerId(Long customerId);
}
