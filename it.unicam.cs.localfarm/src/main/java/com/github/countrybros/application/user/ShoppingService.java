package com.github.countrybros.application.user;


import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotEnoughItemsException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.product.StockService;
import com.github.countrybros.infrastructure.repository.ICartRepository;
import com.github.countrybros.infrastructure.repository.IShoppingItemRepository;
import com.github.countrybros.model.product.Stock;
import com.github.countrybros.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service that performs all the tasks related to the management of the shopping cart and orders.
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

        ShoppingItem shoppingItem = null;
        for (ShoppingItem i : cart.getItems()) {
            if (i.getId() == shoppingItemId) {
                shoppingItem = i;
                break;
            }
        }

        if (shoppingItem == null) {
            throw new NotFoundInRepositoryException("Shopping item not found in cart");
        }

        if (newQuantity < 0) {
            throw new IllegalArgumentException("quantity must be greater than zero");
        }

        if (newQuantity == 0) {
            cart.getItems().remove(shoppingItem);
            shoppingItemRepository.delete(shoppingItem);
            return;
        }

        if (newQuantity > shoppingItem.getAvailableStock()) {
            throw new IllegalArgumentException("max quantity available " + shoppingItem.getAvailableStock());
        }

        shoppingItem.setQuantity(newQuantity);
        shoppingItemRepository.save(shoppingItem);
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
        System.out.println("User " + userId + " removed item " + shoppingItemId + " from cart");
    }




    /**
     * Checks all the item quantities of a cart, comparing them with the actual item in the system,
     * returns a cart with all the Items not present in the marketplace.
     *
     * @param cart The cart to check.
     *
     * @return the cart with excess items.
     *//*
    @Override
    public Cart getExcessItems(Cart cart) {
        if (cart == null || cart.getShoppingItems() == null) {
            throw new ImpossibleRequestException("Cart or items list is null.");
        }


        ArrayList<ShoppingItem> excessItems = new ArrayList<ShoppingItem>();
        Cart excessCart = new Cart();

        for (ShoppingItem shoppingItem : cart.getShoppingItems()) {
            Stock stock = itemService.getStock(shoppingItem.getItem().getId());
            int diff = stock.getQty() - shoppingItem.getQuantity();
            if (diff < 0)
                excessItems.add(new ShoppingItem(excessCart, stock, diff));
        }

        cart.setItems(excessItems);
        return excessCart;
    }

    /**
     * Create an order when a user decides to buy the item inside his cart.
     *
     * @param userId  The user.
     * @param method  The method chosen by the user.
     * @param address The address chosen by the user.
     *//*
    @Override
    public Order checkout(int userId, IPaymentMethod method, ShippingAddress address) {

            Cart cart = getCart(userId);
            Cart excessCart = getExcessItems(cart);

            if (excessCart.getShoppingItems().isEmpty())
                throw new NotEnoughItemsException("Item quantity not available", excessCart);

            paymentService.paymentToMarketplace(method, cart.getTotalAmount());

            User user = userService.getUser(userId);
            Order order = new Order();
            order.setCart(cart);
            order.setAddress(address);
            order.setCustomer(user);
            order.setOrderDate(new Date());
            order.setOrderStatus(OrderStatus.picking);
            orderRepository.save(order);
            return order;
    }*/

}