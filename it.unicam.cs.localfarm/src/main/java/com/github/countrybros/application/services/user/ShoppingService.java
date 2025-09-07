package com.github.countrybros.application.services.user;


import com.github.countrybros.application.abstractions.IPaymentMethod;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repositories.user.ICartRepository;
import com.github.countrybros.infrastructure.repositories.user.IShoppingItemRepository;
import com.github.countrybros.model.order.Order;
import com.github.countrybros.model.order.OrderItem;
import com.github.countrybros.model.stock.Stock;
import com.github.countrybros.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

/**
 * Service that performs all the tasks related to the management of the user cart and orders.
 */
@Service
public class ShoppingService implements IShoppingService {

    private final ICartRepository cartRepository;
    private final IShoppingItemRepository shoppingItemRepository;

    @Autowired
    public ShoppingService(ICartRepository cartRepository, IShoppingItemRepository shoppingItemRepository) {
        this.cartRepository = cartRepository;
        this.shoppingItemRepository = shoppingItemRepository;
    }

    @Override
    public Cart getCart(int userId) {
        return cartRepository.findById(userId).orElseThrow(() -> new NotFoundInRepositoryException("Cart not found, non existent user"));
    }

    @Override
    public void addItemToCart(int userId, Stock stock, int qty) {
        Cart cart = this.getCart(userId);

        if(qty <= 0)
            throw new ImpossibleRequestException("Quantity must be greater than zero");

        ShoppingItem shoppingItem = cart.getShoppingItem(stock.getId());

        if (shoppingItem != null)
            if(shoppingItem.getQuantity() + qty > stock.getQty())
                throw new ImpossibleRequestException("Quantity exceeds stock quantity");
            else
                shoppingItem.setQuantity(shoppingItem.getQuantity() + qty);
        else {
            shoppingItem = new ShoppingItem(stock, qty);
            cart.addItem(shoppingItem);
        }

        shoppingItemRepository.save(shoppingItem);
        cartRepository.save(cart);
    }

    @Override
    public List<Order> getOrders(int userId) {
        return List.of();
    }

    /**
     * returns the list of Items that cannot be bought because there is not enough.
     *
     * @param cart The cart to check.
     * @return the list of items that cannot be bought.
     */
    @Override
    public Cart getExcessItems(Cart cart) {
        return null;
    }

    /**
     * Create an order when a user decides to buy the item inside his cart.
     *
     * @param userId The user.
     */
    @Override
    public Order checkout(int userId) {

        if(!canCheckout(userId))
            return null;

        Cart cart = getCart(userId);
        Order order = new Order();
        OrderItem orderItem = new OrderItem();

        for (ShoppingItem shoppingItem : cart.getShoppingItems())
            orderItem = shoppingItem.toOrderItem();
            order.addItem(orderItem);

        cart.clearItems();
        cartRepository.save(cart);

        return order;
    }

    /**
     * Get the available payment methods.
     *
     * @return A list of payment methods.
     */
    @Override
    public List<IPaymentMethod> getPaymentMethods() {
        return List.of();
    }

    /**
     * Utility method to verify if the required ShoppingItems can be bought.
     *
     * @param userId    the user who want to proceed his cart's checkout.
     * @return          if the checkout can proceed or not.
     */
    private boolean canCheckout(int userId) {

        Cart cart = cartRepository.findById(userId).orElseThrow(() -> new ImpossibleRequestException("User not found"));

        for(ShoppingItem shoppingItem : cart.getShoppingItems())
            if(shoppingItem.getQuantity() > shoppingItem.getStock().getQty())
                return false;

        return true;
    }

    @Override
    public void editQuantityOfItemInCart(int userId, int shoppingItemId, int newQuantity) {
        Cart cart = getCart(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new NotFoundInRepositoryException("Cart is empty or does not exist for user " + userId);
        }

        ShoppingItem foundItem = null;
        for (ShoppingItem i : cart.getItems()) {
            if (i.getId() == shoppingItemId) {
                foundItem = i;
                break;
            }
        }

        if (foundItem == null) {
            throw new NotFoundInRepositoryException("Shopping item not found in cart");
        }

        if (newQuantity < 0) {
            throw new IllegalArgumentException("quantity must be greater than zero");
        }

        if (newQuantity == 0) {
            removeItemFromCart(userId, shoppingItemId);
            return;
        }

        if (newQuantity > foundItem.getAvailableStock()) {
            throw new IllegalArgumentException("max quantity available " + foundItem.getAvailableStock());

        }

        foundItem.setQuantity(newQuantity);
        shoppingItemRepository.save(foundItem);
    }

    @Override
    public void removeItemFromCart(int userId, int shoppingItemId) {
        Cart cart = getCart(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new NotFoundInRepositoryException("Cart is empty or does not exist for user " + userId);
        }

        ShoppingItem foundItem = null;
        for (ShoppingItem item : cart.getItems()) {
            if (item.getId() == shoppingItemId) {
                foundItem = item;
                break;
            }
        }

        if (foundItem == null) {
            throw new NotFoundInRepositoryException(
                    "Shopping item with ID " + shoppingItemId + " not found in user's cart");
        }

        cart.getItems().remove(foundItem);
        shoppingItemRepository.delete(foundItem);
    }

}