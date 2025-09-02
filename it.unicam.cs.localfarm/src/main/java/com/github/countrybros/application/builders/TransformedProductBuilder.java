package com.github.countrybros.application.builders;

import com.github.countrybros.model.item.SimpleProduct;
import com.github.countrybros.model.item.TransformationStep;
import com.github.countrybros.model.item.TransformedProduct;

import java.util.ArrayList;

/**
 * Builder of @transformedProductDetails
 */
public class TransformedProductBuilder extends SimpleProductBuilder {

    /**
     * @inheritDoc
     */
    @Override
    protected SimpleProduct createInstance() {
        return new TransformedProduct();
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

        ((TransformedProduct) product).setSteps(steps);
    }
}
