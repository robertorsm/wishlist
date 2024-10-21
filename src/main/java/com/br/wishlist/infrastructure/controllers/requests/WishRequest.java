package com.br.wishlist.infrastructure.controllers.requests;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WishRequest(@NotNull Long productId,
                          @NotNull Long customerId,
                          @NotNull String title,
                          @NotNull String description,
                          @NotNull BigDecimal price,
                          @NotNull String url,
                          Long quantity) {

}
