package com.github.countrybros.model.user;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class that represents a cart.
 */
public class Cart {
    private ArrayList<ShoppingItem> items;

    public boolean containsItem(int itemId){
        return false;
    }

    public float getTotalAmount() {

        AtomicReference<Float> t = new AtomicReference<>((float) 0);
        items.iterator().forEachRemaining(item->{
            t.updateAndGet(v -> (float) (v + item.getQuantity() * item.getItem().getPrice()));});
        return t.get();
    }

    public void setItems(ArrayList<ShoppingItem> items) {
        this.items = items;
    }

    public ArrayList<ShoppingItem> getItems() {
        return items;
    }
}
