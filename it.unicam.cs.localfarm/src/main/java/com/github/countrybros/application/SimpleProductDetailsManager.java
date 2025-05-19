package com.github.countrybros.application;

import com.github.countrybros.model.Item;
import com.github.countrybros.model.SimpleProductDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Service that performs all the tasks related to the management of the SimpleProductDetails.
 */
public class SimpleProductDetailsManager {
    private Map<Integer, SimpleProductDetails> simpleProductRepository = new HashMap<>();

    public SimpleProductDetails getProductById(int id) {
        return simpleProductRepository.get(id);
    }

    public boolean modifyProduct(SimpleProductDetails product) {
        return false;
    }

    public boolean removeProduct(String id) {
        return false;
    }

    public boolean addProduct(SimpleProductDetails product) {
        return false;
    }

    public List<Item> getCompanyProducts(String companyId) {
        return null;
    }

}
