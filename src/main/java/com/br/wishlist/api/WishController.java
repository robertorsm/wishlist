package com.br.wishlist.api;

import com.br.wishlist.api.contracts.WishApi;
import com.br.wishlist.records.WishRecord;
import com.br.wishlist.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WishController implements WishApi {

    private final WishService wishService;


    @Override
    public ResponseEntity<List<WishRecord>> getAllWishesByCustomerId(Long customerId) {
        return ResponseEntity.ok(wishService.getAllWishesByCustomerId(customerId));
    }

    @Override
    public void createWish(WishRecord wish) {
        wishService.createWish(wish);
    }

    @Override
    public void deleteWish(Long productId, Long customerId) {
        wishService.deleteWish(productId, customerId);
    }

    @Override
    public ResponseEntity<?> existsWishByProductIdAndCustomerId(Long productId, Long customerId) {
        record ExistsWishByProductIdAndCustomerId(Boolean exists) {}
        return ResponseEntity.ok(new ExistsWishByProductIdAndCustomerId(wishService.existsByProductIdAndCustomerId(productId, customerId)));
    }

}
