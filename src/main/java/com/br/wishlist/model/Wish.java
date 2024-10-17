package com.br.wishlist.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Wish {

    @Id
    private Long id;
}
