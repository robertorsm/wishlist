package com.br.wishlist.application.exception;

public class MaxWishLimitReachedException extends RuntimeException{
    public MaxWishLimitReachedException(String message) {
        super(message);
    }
}
