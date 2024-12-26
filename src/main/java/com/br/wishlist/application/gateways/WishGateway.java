package com.br.wishlist.application.gateways;

import com.br.wishlist.domain.entity.Wish;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WishGateway {
    List<Wish> getAllWishesByCustomerId(Long customerId, Pageable pageable);
    Wish createWish(Wish wish);
    void deleteWish(Long productId, Long customerId);
    Boolean existsWishByProductIdAndCustomerId (Long productId, Long customerId);
    Integer countByCustomerId(Long customerId);
}
