package com.github.countrybros.application.product;

import com.github.countrybros.model.product.Bundle;

import java.util.Map;

public class BundleBuilder extends ItemBuilder<Bundle> {

    /**
     * @inheritDoc
     */
    @Override
    protected Bundle createInstance() {
        return new Bundle();
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
    public void setItemsQty(Map<Integer, Integer> items) {

        this.product.setItems(items);
    }
}
