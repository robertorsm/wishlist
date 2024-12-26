package com.br.wishlist.infrastructure.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "wish_list")
@CompoundIndex(def = "{'customerId': 1, 'productId': 1}", unique = true)
@Data
@Builder
public class WishEntity {
    @Id
    private String id;
    private Long productId;
    private Long customerId;
    private String title;
    private String description;
    private BigDecimal price;
    private String url;
    private Long quantity;
}
