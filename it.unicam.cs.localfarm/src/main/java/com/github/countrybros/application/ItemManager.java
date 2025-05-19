package com.github.countrybros.application;

import com.github.countrybros.infrastructure.IItemRepository;
import com.github.countrybros.model.Item;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the item.
 */
public class ItemManager {
  
    private CompanyManager companyManager;
    private IItemRepository itemRepository;


    public boolean addItem(Item item) {
        return false;
    }
    public boolean addQuantityToItem(String itemId, int quantity) {
        return false;
    }
    public boolean removeQuantityToItem(String itemId, int quantity) {
        return false;
    }
    public boolean setPrice(String itemId, double price) {
        return false;
    }
    public Item getItemById(String itemId) {
        return null;
    }
    public List<Item> getItemsBySeller(String sellerId) {
        return null;
    }
}
