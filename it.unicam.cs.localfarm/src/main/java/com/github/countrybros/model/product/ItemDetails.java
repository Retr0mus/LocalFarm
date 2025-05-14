package com.github.countrybros.model.product;

import com.github.countrybros.model.Company;

import java.util.HashMap;
import java.util.Map;

/**
 * Detailed item information.
 */
public class ItemDetails {
    private String name;
    private String description;
    private ItemDetailsStatus status;
    private boolean visibleByPublic = false;
    private Map<Item, Integer> availability = new HashMap<>();
    private Company producer;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemDetailsStatus getStatus() {
        return status;
    }

    public boolean isVisibleByPublic() {
        return visibleByPublic;
    }

    public Map<Item, Integer> getAvailability() {
        return availability;
    }

    public Company getProducer() {
        return producer;
    }
}
