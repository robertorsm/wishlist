package com.br.wishlist.application.exception;

public class WishNotFoundException extends RuntimeException {
    public WishNotFoundException(String message) {
        super(message);
    }
}
