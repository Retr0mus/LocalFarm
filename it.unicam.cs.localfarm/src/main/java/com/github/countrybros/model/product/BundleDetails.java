package com.github.countrybros.model.product;

import com.github.countrybros.model.user.SocialPost;
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
    private List<ItemDetailsQuantity> itemsQty = new ArrayList<>();

    public BundleDetails() {

        super();
    }

    public List<ItemDetailsQuantity> getItemsQty() {

        return itemsQty;
    }

    public void setItemsQty(List<ItemDetailsQuantity> itemsQta) {
        this.itemsQty = itemsQta;
    }

    @Override
    public SocialPost getPost() {
        return new SocialPost();
    }
}
