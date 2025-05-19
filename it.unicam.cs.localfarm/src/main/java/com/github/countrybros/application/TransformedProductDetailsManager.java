package com.github.countrybros.application;

import com.github.countrybros.model.Item;
import com.github.countrybros.model.TransformedProductDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Service that performs all the tasks related to the management of the TransformedProductDetails.
 */
public class TransformedProductDetailsManager {
    private Map<Integer, TransformedProductDetails> trasformedPrductRepository = new HashMap<>();

    public TransformedProductDetails getProductById(int id) {
        return trasformedPrductRepository.get(id);
    }

    public boolean modifyProduct(TransformedProductDetails product) {
        return false;
    }

    public boolean removeProduct(String id) {
        return false;
    }

    public boolean addProduct(TransformedProductDetails product) {
        return false;
    }

    public List<Item> getCompanyProducts(String companyId) {
        return null;
    }

}
