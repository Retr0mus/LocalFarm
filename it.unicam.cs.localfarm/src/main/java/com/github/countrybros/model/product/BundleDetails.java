package com.github.countrybros.model.product;

import java.util.HashMap;
import java.util.Map;

/**
 * Details for a product bundle.
 */
public class BundleDetails extends ItemDetails{
    private int id;
    private Map<ItemDetails, Integer> itemMap = new HashMap<>();

    public int getId() { return id; }
    public Map<ItemDetails, Integer> getItemMap() { return itemMap; }
}
