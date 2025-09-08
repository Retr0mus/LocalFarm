package com.github.countrybros.model.item;

import com.github.countrybros.model.social.SocialPost;
import jakarta.persistence.*;

import java.util.Map;


/**
 * Represents the details of a single bundle of items,
 * contains the @Item correlated with respective quantity.
 */
@Entity
@DiscriminatorValue("bundle")
public class Bundle extends Item {

    /**
     * Represents all the @Item that the bundle contains. the first value is the item ID
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "bundle_items", joinColumns = @JoinColumn(name = "bundle_id"))
    @MapKeyColumn(name = "item_id")
    @Column(name = "quantity")
    private Map<Integer, Integer> items;

    public Bundle() {

        super();
    }

    @Transient
    @Override
    public SocialPost getPost() {
        return new SocialPost(getName(), getDescription(), "link");
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }
}
