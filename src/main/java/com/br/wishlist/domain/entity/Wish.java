package com.br.wishlist.domain.entity;

import java.math.BigDecimal;

public record Wish(Long productId,
                   Long customerId,
                   String title,
                   String description,
                   String url) {
}
