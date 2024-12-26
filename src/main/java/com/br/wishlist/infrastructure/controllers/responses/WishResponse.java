package com.br.wishlist.infrastructure.controllers.responses;

public record WishResponse(Long productId,
                           Long customerId,
                           String title,
                           String description,
                           String url) {
}
