package com.github.countrybros.application;

import com.github.countrybros.model.product.TransformedProductDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the TransformedProductDetails.
 */
public class TransformedProductDetailsManager {
    private Map<Integer, TransformedProductDetails> trasformedPrductRepository = new HashMap<>();

    public TransformedProductDetails getProductById(int id) {
        return trasformedPrductRepository.get(id);
    }

}
