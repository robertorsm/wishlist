package com.br.wishlist.api.contracts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface WishApi {

    @Operation(summary = "Get all wishes")
    @Tag(name = "get", description = "GET all wishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/wishes")
    ResponseEntity<?> getAllWishes();

    @Operation(summary = "Create a wish")
    @Tag(name = "insert", description = "CREATE a wish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/wishes")
    ResponseEntity<?> createWish();


    @Operation(summary = "Delete a wish")
    @Tag(name = "delete", description = "DELETE a wish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/wishes/{id}")
    ResponseEntity<?> deleteWish(@PathVariable @Parameter(description = "wish id") Long id);

    @Operation(summary = "Update a wish")
    @Tag(name = "update", description = "UPDATE a wish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Service error", content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/wishes/{id}")
    ResponseEntity<?> updateWish(@PathVariable @Parameter(description = "wish id") Long id);

}
