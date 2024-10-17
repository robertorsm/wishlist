package com.br.wishlist.api;

import com.br.wishlist.api.contracts.WishApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishController implements WishApi {


    @Override
    public ResponseEntity<?> getAllWishes() {
        return null;
    }

    @Override
    public ResponseEntity<?> createWish() {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteWish(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateWish(Long id) {
        return null;
    }


}
