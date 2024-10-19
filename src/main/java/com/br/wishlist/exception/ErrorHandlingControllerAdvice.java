package com.br.wishlist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingControllerAdvice {

   @ExceptionHandler(TooManyRequestsException.class)
   public ResponseEntity<ErrorResponse> handleUnauthorizedException(final TooManyRequestsException ex) {
       ErrorResponse message = ErrorResponse.builder()
               .statusCode(HttpStatus.TOO_MANY_REQUESTS.value())
               .msg(ex.getMessage())
               .build();
       return new ResponseEntity<>(message, HttpStatus.TOO_MANY_REQUESTS);
   }

}
