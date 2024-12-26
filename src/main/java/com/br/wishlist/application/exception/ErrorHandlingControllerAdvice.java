package com.br.wishlist.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingControllerAdvice {

   @ExceptionHandler(MaxWishLimitReachedException.class)
   public ResponseEntity<ErrorResponse> handleMaxWishLimitReachedException(final MaxWishLimitReachedException ex) {
       ErrorResponse message = ErrorResponse.builder()
               .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
               .msg(ex.getMessage())
               .build();
       return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
   }

    @ExceptionHandler(WishNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWishNotFoundException(WishNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
