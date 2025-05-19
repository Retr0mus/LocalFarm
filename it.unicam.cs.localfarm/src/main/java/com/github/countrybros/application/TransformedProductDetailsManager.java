package com.github.countrybros.application;

import com.github.countrybros.model.TransformedProductDetails;

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

    /**
     * Add a product in the repository.
     *
     * @param product the bundle to insert.
     *
     * @return true if success.
     */
    public boolean addProduct(TransformedProductDetails product) {

        trasformedPrductRepository.put(product.getId(), product);

        return true;
    }
}
