package com.github.countrybros.application.user;


import com.github.countrybros.application.errors.NotEnoughItemsException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.product.ItemService;
import com.github.countrybros.infrastructure.repository.ICartRepository;
import com.github.countrybros.infrastructure.repository.IOrderRepository;
import com.github.countrybros.infrastructure.repository.IShoppingItemRepository;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.user.*;
import com.github.countrybros.model.user.IPaymentMethod;
import jakarta.persistence.Transient;
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

    @Autowired
    private IUserService  userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private IShoppingItemRepository shoppingItemRepository;
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IOrderRepository orderRepository;

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
        Cart cart = getCart(userId);
        Item item = itemService.getItem(itemId);
        if (item == null) {
            throw new NotFoundInRepositoryException("Item with ID " + itemId + " not found.");
        }

        // Verifica se item già presente, aggiorna quantità, altrimenti aggiungi nuovo
        boolean found = false;
        for (ShoppingItem si : cart.getItems().values()) {
            if (si.getItem().getId() == itemId) {
                si.setQuantity(si.getQuantity() + qty);
                shoppingItemRepository.save(si);
                found = true;
                break;
            }
        }

        if (!found) {
            ShoppingItem newItem = new ShoppingItem(cart, item, qty);
            cart.getItems().put(itemId, newItem);
            shoppingItemRepository.save(newItem);
        }

        cartRepository.save(cart);
    }

    @Override
    public void editQuantityOfItemInCart(int userId, int itemId, int newQuantity) {
        Cart cart = getCart(userId);
        ShoppingItem shoppingItem = cart.getItems().get(itemId);
        if (shoppingItem == null) {
            throw new NotFoundInRepositoryException("Shopping item not found in cart");
        }
        shoppingItem.setQuantity(newQuantity);
        shoppingItemRepository.save(shoppingItem);
    }

    @Override
    public void removeItemFromCart(int userId, int itemId, int qty) {
        Cart cart = getCart(userId);
        ShoppingItem shoppingItem = cart.getItems().get(itemId);
        if (shoppingItem == null) {
            throw new NotFoundInRepositoryException("Shopping item not found in cart");
        }
        int updatedQty = shoppingItem.getQuantity() - qty;
        if (updatedQty > 0) {
            shoppingItem.setQuantity(updatedQty);
            shoppingItemRepository.save(shoppingItem);
        } else {
            cart.getItems().remove(itemId);
            shoppingItemRepository.delete(shoppingItem);
        }
    }

    @Override
    public List<Order> getOrders(int userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            throw new NotFoundInRepositoryException("User with ID " + userId + " not found.");
        }

        return orderRepository.findByCustomerUserId(userId);
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

        ArrayList<ShoppingItem> excessItems = new ArrayList<ShoppingItem>();
        Cart excessCart = new Cart();

        for (ShoppingItem shoppingItem : cart.getItems().values()) {
            Item item = itemService.getItem(shoppingItem.getItem().getId());
            int diff = item.getQty() - shoppingItem.getQuantity();
            if (diff < 0)
                excessItems.add(new ShoppingItem(excessCart, item, diff));
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
     */
    @Override
    public Order checkout(int userId, IPaymentMethod method, ShippingAddress address) {

            Cart cart = getCart(userId);
            Cart excessCart = getExcessItems(cart);

            if (excessCart.getItems().isEmpty())
                throw new NotEnoughItemsException("Item quantity not available", excessCart);

            //TODO: User as parameter, maybe
            paymentService.buy(userId, method, cart.getTotalAmount());

            User user = userService.getUser(userId);
            Order order = new Order();
            order.setCart(cart);
            order.setAddress(address);
            order.setCustomer(user);
            order.setOrderDate(new Date());
            order.setOrderStatus(OrderStatus.picking);
            orderRepository.save(order);
            return order;
    }

}