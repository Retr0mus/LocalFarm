package com.github.countrybros.application.user;


import com.github.countrybros.application.product.ItemService;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.user.Cart;
import com.github.countrybros.model.user.Order;
import com.github.countrybros.model.user.ShoppingItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that performs all the tasks related to the management of the shopping cart and orders.
 */
@Service
public class ShoppingService implements IShoppingService {

    private IUserService  userService;
    //TODO: update with interface
    private ItemService itemService;

    @Override
    public Cart getCart(int userId) { return null; }

    @Override
    public boolean addItemToCart(int userId, int itemId, int qty) { return false; }

    @Override
    public boolean editQuantityOfItemInCart(int userId, int itemId, int newQuantity) { return false; }

    @Override
    public boolean removeItemFromCart(int userId, int itemId, int qty) { return false; }

    @Override
    public List<Order> getOrders(int userId) { return null; }

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

}