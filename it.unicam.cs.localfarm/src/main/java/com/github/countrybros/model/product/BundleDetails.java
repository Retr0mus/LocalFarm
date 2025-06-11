package com.github.countrybros.model.product;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents the details of a single bundle of items,
 * contains the @Item correlated with respective quantity.
 */
@Entity
@DiscriminatorValue("bundleDetails")
public class BundleDetails extends ItemDetails {

    /**
     * Represents all the @ItemDetails that
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemDetailsQuantity> itemsQta = new ArrayList<>();

    public BundleDetails() {

        super();
    }

    public List<ItemDetailsQuantity> getItemsQta() {

        return itemsQta;
    }

    public void setItemsQta(List<ItemDetailsQuantity> itemsQta) {
        this.itemsQta = itemsQta;
    }
}
