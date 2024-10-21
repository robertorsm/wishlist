package com.br.wishlist.infrastructure.gateways;

import com.br.wishlist.domain.entity.Wish;
import com.br.wishlist.infrastructure.persistence.WishEntity;
import org.springframework.stereotype.Component;

@Component
public class WishEntityMapper {
    public WishEntity toDocument(Wish wish) {
        return WishEntity.builder()
                .productId(wish.productId())
                .customerId(wish.customerId())
                .title(wish.title())
                .description(wish.description())
                .price(wish.price())
                .url(wish.url())
                .quantity(wish.quantity())
                .build();
    }

    public Wish documentToDomain(WishEntity wishEntity) {
        return new Wish(wishEntity.getProductId(),
                wishEntity.getCustomerId(),
                wishEntity.getTitle(),
                wishEntity.getDescription(),
                wishEntity.getPrice(),
                wishEntity.getUrl(),
                wishEntity.getQuantity());
    }
}
