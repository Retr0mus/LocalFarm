package com.github.countrybros.application;

import com.github.countrybros.model.BundleDetails;
import com.github.countrybros.model.Item;

import java.util.HashMap;

public class BundleDetailsBuilder extends ItemDetailsBuilder<BundleDetails>{

    /**
     * @inheritDoc
     */
    @Override
    protected BundleDetails createInstance() {
        return new BundleDetails();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void reset() {

        this.product = this.createInstance();
    }

    /**
     * Sets the items of the product
     *
     * @param items map of the items linked to their quantity
     */
    public void setItemsMap(HashMap<Item, Integer> items) {

        this.product.setItemsMap(items);
    }
}
