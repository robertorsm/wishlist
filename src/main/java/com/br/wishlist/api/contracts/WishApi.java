package com.br.wishlist.api.contracts;

import com.br.wishlist.records.WishRecord;
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
    @Tag(name = "get", description = "GET all wishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/wishes/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<WishRecord>> getAllWishesByCustomerId(@PathVariable @Parameter(description = "customerId") Long customerId);

    @Operation(summary = "Create a wish")
    @Tag(name = "insert", description = "CREATE a wish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "429", description = "Too Many Requests", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/wishes")
    @ResponseStatus(HttpStatus.CREATED)
    void createWish(@Valid @RequestBody final WishRecord wish);


    @Operation(summary = "Delete a wish")
    @Tag(name = "delete", description = "DELETE a wish")
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
    @Tag(name = "get", description = "GET a wish by productId and customerId")
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
