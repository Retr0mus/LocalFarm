package com.github.countrybros.application;

import com.github.countrybros.model.SimpleProductDetails;

import java.util.HashMap;
import java.util.Map;


/**
 * Service that performs all the tasks related to the management of the SimpleProductDetails.
 */
public class SimpleProductDetailsManager {
    private Map<Integer, SimpleProductDetails> simpleProductRepository = new HashMap<>();

    public SimpleProductDetails getProductById(int id) {
        return simpleProductRepository.get(id);
    }

    /**
     * Add a product in the repository.
     *
     * @param productDetails the bundle to insert.
     *
     * @return true if success.
     */
    public boolean addSimpleProductDetails(SimpleProductDetails productDetails) {

        simpleProductRepository.put(productDetails.getId(), productDetails);

        return true;
    }
}
