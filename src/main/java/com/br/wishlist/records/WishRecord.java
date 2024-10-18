package com.br.wishlist.records;

import com.br.wishlist.model.Wish;

import java.math.BigDecimal;

public record WishRecord( Long productId,
                          Long customerId,
                          String title,
                          String description,
                          BigDecimal price,
                          String url,
                          Long quantity) {

    public Wish toDocument() {
        return Wish.builder()
                .productId(productId)
                .customerId(customerId)
                .title(title)
                .description(description)
                .price(price)
                .url(url)
                .quantity(quantity)
                .build();
    }

}
