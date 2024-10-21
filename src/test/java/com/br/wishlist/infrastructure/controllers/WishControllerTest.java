package com.br.wishlist.infrastructure.controllers;

import com.br.wishlist.BaseTest;
import com.br.wishlist.WishlistApplication;
import com.br.wishlist.infrastructure.controllers.requests.WishRequest;
import com.br.wishlist.infrastructure.controllers.responses.WishResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WishlistApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class WishControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Create a new wish should be OK")
    @Order(1)
    public void createNewWish() throws Exception {
        var request = mapper.writeValueAsString(createIntegrationResponse("requests/create_wish_body.json", WishRequest.class));
        var response = mapper.writeValueAsString(createIntegrationResponse("responses/create_wish_response.json", WishResponse.class));

        MvcResult mvcResult = mockMvc.perform(post("/wish-list/wishes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        String actualResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(response, actualResponse);
    }

    @Test
    @DisplayName("Get all wishes for a specific customer should be OK")
    @Order(2)
    void getAllWishesByCustomerId() throws Exception  {
         this.mockMvc.perform(get("/wish-list/wishes/{customerId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[*]").isArray())
                .andExpect(jsonPath("[*]", hasItems(notNullValue())))
                .andExpect(jsonPath("[0].customerId").exists())
                .andExpect(jsonPath("[0].title").exists());

    }



    @Test
    @DisplayName("Check if a wish exists for a specific customer should be OK")
    @Order(3)
    void existsWishByProductIdAndCustomerId() throws Exception {
        WishRequest request = createIntegrationResponse("requests/create_wish_body.json", WishRequest.class);

        mockMvc.perform(get("/wish-list/wishes/{productId}/{customerId}",request.productId(),request.customerId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.exists").value(true));
    }

    @Test
    @DisplayName("Check if a wish exists for a specific customer should NOT BE OK")
    @Order(4)
    void notExistsWishByProductIdAndCustomerId() throws Exception {
        WishRequest request = createIntegrationResponse("requests/create_wish_body.json", WishRequest.class);

        mockMvc.perform(get("/wish-list/wishes/{productId}/{customerId}",1,2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.exists").value(false));
    }

    @Test
    @DisplayName("Create a new wish shouldn't work because of bad request")
    @Order(5)
    public void createNewWishBadRequest() throws Exception {
        var request = mapper.writeValueAsString(createIntegrationResponse("requests/create_wish_bad_request.json", WishRequest.class));
        mockMvc.perform(post("/wish-list/wishes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Delete a wish should be OK")
    @Order(6)
    void deleteWish() throws Exception {
        var request = mapper.writeValueAsString(createIntegrationResponse("requests/create_wish_body.json", WishRequest.class));
        mockMvc.perform(delete("/wish-list/wishes/{productId}/{customerId}", 1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}