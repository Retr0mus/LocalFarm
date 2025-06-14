package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Cart;

public interface ICartService {
    Cart getCartById(int cartId);

    void save(Cart cart);
}
