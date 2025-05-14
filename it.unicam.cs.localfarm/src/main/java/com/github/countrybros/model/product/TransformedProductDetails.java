package com.github.countrybros.model.product;

import java.util.ArrayList;
import java.util.List;

/**
 * Details for a transformed product, composed of simple products.
 */
public class TransformedProductDetails extends SimpleProductDetails {
    private List<SimpleProductDetails> ingredients = new ArrayList<>();

    public List<SimpleProductDetails> getIngredients() { return ingredients; }


}
