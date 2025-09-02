package com.github.countrybros.application.models.dtos.item;

import java.util.Map;

public class BundleDto extends ItemDto{
    private Map<Integer, Integer> items;

    public BundleDto() {
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }
}
