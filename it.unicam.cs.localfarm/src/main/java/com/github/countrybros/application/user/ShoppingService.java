package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.IShoppingItemRepository;
import com.github.countrybros.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Service that performs all the tasks related to the management of the shopping cart and orders.
 */
@Service
public class ShoppingService implements IShoppingService {


    @Autowired
    private IUserService userService;
    @Autowired
    private IShoppingItemRepository shoppingItemRepository;

    @Override
    public Cart getCart(int userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            throw new NotFoundInRepositoryException("User with ID " + userId + " not found.");
        }
        return user.getCart();
    }

    @Override
    public void addItemToCart(int userId, int itemId, int qty) {
//        Cart cart = getCart(userId);
//        Stock stock = itemService.getItem(itemId);
//        if (stock == null) {
//            throw new NotFoundInRepositoryException("Item with ID " + itemId + " not found.");
//        }
//
//        // Verifica se item già presente, aggiorna quantità, altrimenti aggiungi nuovo
//        boolean found = false;
//        for (ShoppingItem si : cart.getItems()) {
//            if (si.getItem().getId() == itemId) {
//                si.setQuantity(si.getQuantity() + qty);
//                shoppingItemRepository.save(si);
//                found = true;
//                break;
//            }
//        }
//
//        if (!found) {
//            ShoppingItem newItem = new ShoppingItem(cart, stock, qty);
//            cart.getItems().add(itemId, newItem);
//            shoppingItemRepository.save(newItem);
//        }
//
//        cartRepository.save(cart);
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


    @Override
    public List<Order> getOrders(int userId) {
//        User user = userService.getUser(userId);
//        if (user == null) {
//            throw new NotFoundInRepositoryException("User with ID " + userId + " not found.");
//        }
//
//        return orderRepository.findByCustomerUserId(userId);
        return null;
   }

    /**
     * Checks all the item quantities of a cart, comparing them with the actual item in the system,
     * returns a cart with all the Items not present in the marketplace.
     *
     * @param cart The cart to check.
     *
     * @return the cart with excess items.
     */
    @Override
    public Cart getExcessItems(Cart cart) {
//        if (cart == null || cart.getItems() == null) {
//            throw new ImpossibleRequestException("Cart or items list is null.");
//        }
//
//
//        ArrayList<ShoppingItem> excessItems = new ArrayList<ShoppingItem>();
//        Cart excessCart = new Cart();
//
//        for (ShoppingItem shoppingItem : cart.getItems()) {
//            Stock stock = itemService.getItem(shoppingItem.getItem().getId());
//            int diff = stock.getQty() - shoppingItem.getQuantity();
//            if (diff < 0)
//                excessItems.add(new ShoppingItem(excessCart, stock, diff));
//        }
//
//        cart.setItems(excessItems);
//        return excessCart;
        return null;
    }

    /**
     * Create an order when a user decides to buy the item inside his cart.
     *
     * @param userId  The user.
     * @param method  The method chosen by the user.
     * @param address The address chosen by the user.
     */
    @Override
    public Order checkout(int userId, IPaymentMethod method, ShippingAddress address) {

        return null;
    }

}