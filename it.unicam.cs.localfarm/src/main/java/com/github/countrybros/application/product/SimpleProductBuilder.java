package com.github.countrybros.application.product;

import com.github.countrybros.model.product.Certification;
import com.github.countrybros.model.product.SimpleProduct;

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
     * Sets the certification of the product
     *
     * @param certifications List of certifications
     */
    public void setCertifications(ArrayList<Certification> certifications) {

        this.product.setCertifications(certifications);
    }
}
