package com.br.wishlist.infrastructure.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WishRequest(@NotNull Long productId,
                          @NotNull Long customerId,
                          @NotBlank String title,
                          @NotBlank String description,
                          @NotBlank String url) {

}
