package com.br.wishlist.infrastructure.controllers.mappers;

import com.br.wishlist.domain.entity.Wish;
import com.br.wishlist.infrastructure.controllers.requests.WishRequest;
import com.br.wishlist.infrastructure.controllers.responses.WishResponse;
import org.springframework.stereotype.Component;

@Component
public class WishDTOMapper {

    public WishResponse toResponse(Wish wish){
        return new WishResponse(wish.productId(),
                                wish.customerId(),
                                wish.title(),
                                wish.description(),
                                wish.url());
    }

    public Wish toDomain(WishRequest wishRequest){
        return new Wish(wishRequest.productId(),
                        wishRequest.customerId(),
                        wishRequest.title(),
                        wishRequest.description(),
                        wishRequest.url());
    }
}
