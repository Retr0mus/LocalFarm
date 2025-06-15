package com.github.countrybros.application.product;

import com.github.countrybros.model.product.BundleDetails;
import com.github.countrybros.model.product.ItemDetailsQuantity;

import java.util.ArrayList;

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
    public void setItemsQty(ArrayList<ItemDetailsQuantity> items) {

        this.product.setItemsQty(items);
    }
}
