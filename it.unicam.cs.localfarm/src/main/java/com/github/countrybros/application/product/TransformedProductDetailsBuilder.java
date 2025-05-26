package com.github.countrybros.application.product;

import com.github.countrybros.model.product.SimpleProductDetails;
import com.github.countrybros.model.product.TransformationStep;
import com.github.countrybros.model.product.TransformedProductDetails;

import java.util.ArrayList;

/**
 * Builder of @transformedProductDetails
 */
public class TransformedProductDetailsBuilder extends SimpleProductDetailsBuilder {

    /**
     * @inheritDoc
     */
    @Override
    protected SimpleProductDetails createInstance() {
        return new TransformedProductDetails();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void reset() {

        this.product = this.createInstance();
    }

    /**
     * Sets the steps of the transformation
     *
     * @param steps List of @TransformationSteps
     */
    public void setTransformationSteps(ArrayList<TransformationStep> steps) {

        ((TransformedProductDetails) product).setSteps(steps);
    }
}
