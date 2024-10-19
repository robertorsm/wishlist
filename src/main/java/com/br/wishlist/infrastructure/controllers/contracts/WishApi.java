package com.br.wishlist.infrastructure.controllers.contracts;

import com.br.wishlist.infrastructure.controllers.requests.WishRequest;
import com.br.wishlist.infrastructure.controllers.responses.WishResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/wish-list")
public interface WishApi {

    @Operation(summary = "Get all wishes")
    @Tag(name = "GET", description = "Get all wishes by customer ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/wishes/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<WishResponse>> getAllWishesByCustomerId(@PathVariable @Parameter(description = "customerId") Long customerId);

    @Operation(summary = "Create a wish")
    @Tag(name = "CREATE", description = "Create a new wish in customerId wish-list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "429", description = "Too Many Requests", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/wishes")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<WishResponse> createWish(@Valid @RequestBody final WishRequest wish);


    @Operation(summary = "Delete a wish")
    @Tag(name = "DELETE", description = "Delete a wish form customerId wish -list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/wishes/{productId}/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteWish(@PathVariable @Parameter(description = "productId") Long productId,
                                 @PathVariable @Parameter(description = "customerId") Long customerId);

    @Operation(summary = "Check if exists wish for a customerId")
    @Tag(name = "GET", description = "Check if a specific productId existis in customerId wish-list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/wishes/{productId}/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> existsWishByProductIdAndCustomerId(@PathVariable @Parameter(description = "Product ID") Long productId,
                                              @PathVariable @Parameter(description = "Customer ID") Long customerId);

}
