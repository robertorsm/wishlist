package com.br.wishlist.infrastructure.controllers;

import com.br.wishlist.application.gateways.WishGateway;
import com.br.wishlist.application.usecases.WishUseCase;
import com.br.wishlist.domain.entity.Wish;
import com.br.wishlist.infrastructure.controllers.requests.WishRequest;
import com.br.wishlist.infrastructure.controllers.responses.WishResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONValue;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private WishUseCase wishUseCase;

    @MockBean
    private WishGateway wishGateway;


    @Test
    @DisplayName("Get all wishes for a specific customer should be OK")
    @Order(1)
    void getAllWishesByCustomerId() throws Exception  {
        var response = mapper.writeValueAsString(createIntegrationResponse("responses/create_wish_response.json", WishResponse.class));

        when(wishUseCase.getAllWishesByCustomerId(anyLong())).thenReturn(createIntegrationResponseList("responses/get_all_wishes_by_customer_id.json", Wish.class));

         mockMvc.perform(get("/wish-list/wishes/{customerId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[*]").isArray())
                .andExpect(jsonPath("[*]", hasItems(notNullValue())))
                .andExpect(jsonPath("[0].customerId").exists())
                .andExpect(jsonPath("[0].title").exists());

    }

    @Test
    @DisplayName("Create a new wish should be OK")
    @Order(2)
    public void createNewWish() throws Exception {
        var request = mapper.writeValueAsString(createIntegrationResponse("requests/create_wish_body.json", WishRequest.class));
        var response = mapper.writeValueAsString(createIntegrationResponse("responses/create_wish_response.json", WishResponse.class));

        when(wishUseCase.createWish(Mockito.any(Wish.class))).thenReturn(mapper.readValue(response, Wish.class));

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
    @DisplayName("Delete a wish should be OK")
    @Order(3)
    void deleteWish() throws Exception {
        var request = mapper.writeValueAsString(createIntegrationResponse("requests/create_wish_body.json", WishRequest.class));
        mockMvc.perform(delete("/wish-list/wishes/{productId}/{customerId}", 1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Check if a wish existis for a specific customer should OK")
    @Order(4)
    void existsWishByProductIdAndCustomerId() throws Exception {
        WishRequest request = createIntegrationResponse("requests/create_wish_body.json", WishRequest.class);

        mockMvc.perform(get("/wish-list/wishes/{productId}/{customerId}",request.productId(),request.customerId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.exists").value(false));
    }

    private <T> T createIntegrationResponse(String path, Class<T> element) {
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(String.format("/%s", path));
        final Object request = JSONValue.parse(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
        return mapper.convertValue(request, element);
    }

    public <T> List<T> createIntegrationResponseList(String path, Class<T> element) throws IOException {
        try (InputStream resourceAsStream = getClass().getResourceAsStream(String.format("/%s", path))) {
            String jsonContent = new String(resourceAsStream.readAllBytes(), "UTF-8");
            return mapper.readValue(jsonContent, mapper.getTypeFactory().constructCollectionType(List.class, element));
        }
    }

}