package com.github.countrybros.application.product;

import com.github.countrybros.application.user.CompanyService;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.IItemRepository;
import com.github.countrybros.model.product.Item;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the item.
 */
public class ItemService {
  
    private CompanyService companyManager;
    private IItemRepository itemRepository;


    public boolean addItem(Item item) {
        return false;
    }

    /**
     * Adds the specified quantity to an @Item.
     *
     * @param itemId ID of the item.
     * @param quantity Quantity.
     *
     * @return true if the item exists.
     */
    public boolean addQuantityToItem(int itemId, int quantity) {

        if (!this.itemRepository.exists(itemId))
            throw new NotFoundInRepositoryException("Item not found");

        Item item = this.itemRepository.get(itemId);
        item.setQty(item.getQty() + quantity);

        this.itemRepository.save(item);
        return true;
    }

    /**
     * Subtract the specified quantity to an @Item.
     *
     * @param itemId ID of the item.
     * @param quantity Quantity.
     *
     * @return true if the item exists AND the subtraction gets to a non-negative quantity.
     */
    public boolean removeQuantityToItem(int itemId, int quantity) {

        if (!this.itemRepository.exists(itemId))
            throw new NotFoundInRepositoryException("Item not found");

        Item item = this.itemRepository.get(itemId);
        if (item.getQty() < quantity)
            return false;
        item.setQty(item.getQty() - quantity);

        this.itemRepository.save(item);
        return true;
    }

    public boolean setPrice(int itemId, double price) {
        return false;
    }

    public Item getItem(int itemId) {
        return null;
    }

    public List<Item> getItemsBySeller(int companyId) {
        return null;
    }
}
