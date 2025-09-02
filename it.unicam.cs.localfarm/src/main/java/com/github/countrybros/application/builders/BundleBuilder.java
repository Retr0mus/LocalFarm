package com.github.countrybros.application.builders;

import com.github.countrybros.model.item.Bundle;
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
     * Sets the items of the item
     *
     * @param items map of the items linked to their quantity
     */
    public void setItemsQty(Map<Integer, Integer> items) {

        this.product.setItems(items);
    }
}
