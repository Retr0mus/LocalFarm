package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.ICartRepository;
import com.github.countrybros.model.user.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Cart getCartById(int cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Cart with ID " + cartId + " not found."));
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

}
