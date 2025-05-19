package com.github.countrybros.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the details of a generic @Item that can be sold in the marketplace.
 */
public class ItemDetails {

    /**
     * ID of the item:
     */
    private int Id;

    /**
     * Name of the item.
     */
    private String name;

    /**
     * Description of the item.
     */
    private String description;

    /**
     * Status of the item.
     */
    private ItemDetailsStatus status;

    /**
     * Represents the visibility of the item,
     * true if it can be selled, false otherwise.
     */
    private boolean visibleByPublic = false;

    /**
     * Map that contains every @Item that contains this item
     */
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

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public ItemDetailsStatus getStatus() {
        return status;
    }

    public void setStatus(ItemDetailsStatus status) {}

    public boolean isVisibleByPublic() {
        return visibleByPublic;
    }

    public void setVisibleByPublic(boolean visibleByPublic) {}

    public Map<Item, Integer> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<Item, Integer> availability) {}

    public Company getProducer() {
        return producer;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
