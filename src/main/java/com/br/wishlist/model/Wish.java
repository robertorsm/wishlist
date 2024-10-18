package com.br.wishlist.model;

import com.br.wishlist.records.WishRecord;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "wish_list")
@CompoundIndex(def = "{'productId': 1, 'customerId': 1}", unique = true)
@Data
@Builder
public class Wish {

    @Id
    private String id;

//    @Indexed(unique = true)
    private Long productId;
//    @Indexed(unique = true)
    private Long customerId;
    private String title;
    private String description;
    private BigDecimal price;
    private String url;
    private Long quantity;

    public WishRecord documentToRecord() {
        return new WishRecord(getProductId(),
                getCustomerId(),
                getTitle(),
                getDescription(),
                getPrice(),
                getUrl(),
                getQuantity());
    }
}
