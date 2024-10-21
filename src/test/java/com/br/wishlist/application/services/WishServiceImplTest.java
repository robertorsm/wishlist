package com.br.wishlist.application.services;

import com.br.wishlist.BaseTest;
import com.br.wishlist.application.exception.TooManyRequestsException;
import com.br.wishlist.application.gateways.WishGateway;
import com.br.wishlist.domain.entity.Wish;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class WishServiceImplTest extends BaseTest {

    @Mock
    private WishGateway wishGateway;

    @InjectMocks
    private WishServiceImpl wishServiceImpl;


    @Test
    @DisplayName("Should persist a wish to customerId")
    void shouldCreateWish() {
        var wishRequest = createIntegrationResponse("requests/create_wish_body.json", Wish.class);
        when(wishGateway.createWish(Mockito.any(Wish.class))).thenReturn(wishRequest);
        var response = wishServiceImpl.createWish(wishRequest);
        assertNotNull(response);
        assertThat(wishRequest).usingRecursiveComparison().isEqualTo(response);
    }

    @Test
    @DisplayName("Should not persist a wish to customerId because of limit of wishes")
    void shouldNotCreateWishByNegotiateException() {
        var wishRequest = createIntegrationResponse("requests/create_wish_body.json", Wish.class);
        when(wishGateway.countByCustomerId(anyLong())).thenReturn(20);
        assertThrows(TooManyRequestsException.class,() -> wishServiceImpl.createWish(wishRequest));

    }

    @Test
    @DisplayName("Should get all wishes for a specific customerId")
    void getAllWishesByCustomerId() {
        var wishResponse = createIntegrationResponseList("responses/get_all_wishes_by_customer_id.json", Wish.class);
        when(wishGateway.getAllWishesByCustomerId(anyLong())).thenReturn(wishResponse);
        var response = wishServiceImpl.getAllWishesByCustomerId(1L);
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertThat(wishResponse).usingRecursiveComparison().isEqualTo(response);

    }
}