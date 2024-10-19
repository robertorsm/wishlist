package com.br.wishlist.infrastructure.controllers;

import com.br.wishlist.application.usecases.WishUseCase;
import com.br.wishlist.domain.entity.Wish;
import com.br.wishlist.infrastructure.controllers.contracts.WishApi;

import com.br.wishlist.infrastructure.controllers.mappers.WishDTOMapper;
import com.br.wishlist.infrastructure.controllers.requests.WishRequest;
import com.br.wishlist.infrastructure.controllers.responses.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WishController implements WishApi {

    private final WishDTOMapper wishDTOMapper;

    private final WishUseCase wishUseCase;

    @Override
    public ResponseEntity<List<WishResponse>> getAllWishesByCustomerId(Long customerId) {
        List<WishResponse> wishResponses = wishUseCase.getAllWishesByCustomerId(customerId).stream()
                .map(wishDTOMapper::toResponse).toList();
        return ResponseEntity.ok(wishResponses);
    }

    @Override
    public ResponseEntity<WishResponse> createWish(WishRequest wishRequest) {
        Wish wish = wishDTOMapper.toDomain(wishRequest);
        WishResponse wishResponse = wishDTOMapper.toResponse(wishUseCase.createWish(wish));
        return ResponseEntity.ok(wishResponse);
    }

    @Override
    public void deleteWish(Long productId, Long customerId) {
        wishUseCase.deleteWishByProductIdAndCustomerId(productId, customerId);
    }

    @Override
    public ResponseEntity<?> existsWishByProductIdAndCustomerId(Long productId, Long customerId) {
        record ExistsWishByProductIdAndCustomerId(Boolean exists) {}
        return ResponseEntity.ok(new ExistsWishByProductIdAndCustomerId(wishUseCase.existsByProductIdAndCustomerId(productId, customerId)));
    }

}
