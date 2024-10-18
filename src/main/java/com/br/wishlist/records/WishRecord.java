package com.br.wishlist.records;

import com.br.wishlist.model.Wish;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WishRecord( @NotNull Long productId,
                          @NotNull Long customerId,
                          @NotNull String title,
                          @NotNull String description,
                          @NotNull BigDecimal price,
                          @NotNull String url,
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
