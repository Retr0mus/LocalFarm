package com.github.countrybros.application.errors;

import com.github.countrybros.model.user.Cart;

public class NotEnoughItemsException extends RuntimeException {

    private final Cart excessCart;
    public NotEnoughItemsException(String message, Cart cart) {

        super(message);
        this.excessCart = cart;
    }
}
