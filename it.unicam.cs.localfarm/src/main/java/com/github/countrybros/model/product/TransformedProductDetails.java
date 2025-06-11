package com.github.countrybros.model.product;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Details for a transformed product, composed of simple products.
 */
@Entity
@DiscriminatorValue("transformedProductDetails")
public class TransformedProductDetails extends SimpleProductDetails {

    /**
     * Steps that defines the transformation process.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<TransformationStep> steps = new ArrayList<>();

    public List<TransformationStep> getSteps() {
        return steps;
    }

    public void setSteps(List<TransformationStep> steps) {
        this.steps = steps;
    }
}
