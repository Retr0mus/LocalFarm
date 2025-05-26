package com.github.countrybros.application;

import com.github.countrybros.model.Certification;
import com.github.countrybros.model.SimpleProductDetails;

import java.util.ArrayList;

/**
 * Builder of @SimpleProductDetails
 */
public class SimpleProductDetailsBuilder extends ItemDetailsBuilder<SimpleProductDetails>{

    /**
     * @inheritDoc
     */
    @Override
    protected SimpleProductDetails createInstance() {
        return new SimpleProductDetails();
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
