package com.github.countrybros.infrastructure;

import com.github.countrybros.model.Item;

import java.util.List;

/**
 * Interface for item data access and manipulation.
 */
public interface  IItemRepository {

    boolean addItem(Item item);

    boolean removeItem(String itemId);

    boolean setItemQuantity(String itemId, int quantity);

    Item getItemById(String itemId);

    List<Item> getItemsBySeller(String sellerId);
}
