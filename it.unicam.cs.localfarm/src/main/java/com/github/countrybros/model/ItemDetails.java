package com.github.countrybros.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the details of a generic @Item that can be sold in the marketplace.
 */
public class ItemDetails {
    private String name;
    private String description;
    private ItemDetailsStatus status;
    private boolean visibleByPublic = false;
    private Map<Item, Integer> availability = new HashMap<>();


    public ItemDetails(String name, String description, Company producer) {

        this.name = name;
        this.description = description;
        this.producer = producer;

        this.availability = new HashMap<Item, Integer>();
        this.status = ItemDetailsStatus.awaitingReview;
        //TODO AcceptanceSubmission creation
        this.visibleByPublic = false;
    }

    /**
     * The producer of the item.
     */
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
