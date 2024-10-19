package com.br.wishlist.infrastructure.controllers.responses;

import java.math.BigDecimal;

public record WishResponse(Long productId,
                           Long customerId,
                           String title,
                           String description,
                           BigDecimal price,
                           String url,
                           Long quantity) {
}
