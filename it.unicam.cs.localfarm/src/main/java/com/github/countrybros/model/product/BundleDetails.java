package com.github.countrybros.model.product;

import jakarta.persistence.Entity;

import java.util.HashMap;


/**
 * Represents the details of a single bundle of items,
 * contains the @Item correlated with respective quantity.
 */
@Entity
public class BundleDetails extends ItemDetails {

    /**
     * Represents all the @ItemDetails that
     */
    private HashMap<Item, Integer> itemsMap;

    public BundleDetails() {

        super();
    }

    public HashMap<Item, Integer> getItemsMap() {

        return itemsMap;
    }

    public void setItemsMap(HashMap<Item, Integer> itemsMap) {
        this.itemsMap = itemsMap;
    }
}
