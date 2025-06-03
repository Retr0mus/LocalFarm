package com.github.countrybros.model.product;

import com.github.countrybros.model.user.Company;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the details of a generic @Item that can be sold in the marketplace.
 */
@MappedSuperclass
public abstract class ItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;

    private String description;

    private ItemDetailsStatus status;

    private boolean visibleByPublic = false;

    // It does not work because it needs @Entity on Company, remind to put it.
    @ManyToOne(fetch = FetchType.EAGER)
    private Company producer;


    public ItemDetails() {
        this.status = ItemDetailsStatus.awaitingReview;
        this.visibleByPublic = false;
    }


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

    public void setProducer(Company producer) {
        this.producer = producer;
    }
}
