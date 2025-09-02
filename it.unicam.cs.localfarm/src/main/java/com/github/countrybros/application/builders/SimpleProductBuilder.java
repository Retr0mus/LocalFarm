package com.github.countrybros.application.builders;

import com.github.countrybros.model.item.Certification;
import com.github.countrybros.model.item.SimpleProduct;

import java.util.ArrayList;

/**
 * Builder of @SimpleProductDetails
 */
public class SimpleProductBuilder extends ItemBuilder<SimpleProduct> {

    /**
     * @inheritDoc
     */
    @Override
    protected SimpleProduct createInstance() {
        return new SimpleProduct();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void reset() {

        this.product = this.createInstance();
    }

    /**
     * Sets the certification of the item
     *
     * @param certifications List of certifications
     */
    public void setCertifications(ArrayList<Certification> certifications) {

        this.product.setCertifications(certifications);
    }
}
