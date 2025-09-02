package com.github.countrybros.application.builders;

import com.github.countrybros.model.item.ItemStatus;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.item.Item;

/**
 * Creates an @ItemDetails
 */
public abstract class ItemBuilder<T extends Item> implements IItemBuilder {

    /**
     * The Item to build.
     */
     T product;


     //TODO: resolve create/get
    /**
     * Creates the right instance.
     *
     * @return the instance of the correct subtype
     */
    protected abstract T createInstance();


    public ItemBuilder() {

        this.reset();
    }

    @Override
    public void reset() {

        product = this.createInstance();
    }

    @Override
    public Item getResult() {

        //TODO: impostare a awaitingReview, o cambiare gli stati
        product.setVisibleByPublic(false);
        product.setStatus(ItemStatus.awaitingReview);
        return product;
    }

    @Override
    public void setName(String productName) {

        product.setName(productName);
    }

    @Override
    public void setDescription(String productDescription) {

        product.setDescription(productDescription);
    }

    @Override
    public void setProducer(Company producer) {

        product.setProducer(producer);
    }
}
