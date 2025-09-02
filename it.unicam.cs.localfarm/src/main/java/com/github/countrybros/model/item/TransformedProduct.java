package com.github.countrybros.model.item;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Details for a transformed item, composed of simple products.
 */
@Entity
@DiscriminatorValue("transformedProduct")
public class TransformedProduct extends SimpleProduct {

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
