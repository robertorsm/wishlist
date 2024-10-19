package com.br.wishlist.application.usecases;

import com.br.wishlist.domain.entity.Wish;

import java.util.List;

public interface WishUseCase {

    List<Wish> getAllWishesByCustomerId(Long customerId);
    Wish createWish(Wish wish);
    void deleteWishByProductIdAndCustomerId(Long productId, Long customerId);
    Boolean existsByProductIdAndCustomerId(Long productId, Long customerId);

}
