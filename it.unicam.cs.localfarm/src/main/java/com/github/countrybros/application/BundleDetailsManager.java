package com.github.countrybros.application;

import com.github.countrybros.model.BundleDetails;
import com.github.countrybros.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the BundleDetails.
 */
public class BundleDetailsManager {

    private Map<Integer, BundleDetails> bundleRepository = new HashMap<>();

    public BundleDetails getBundleById(int id) {
        return bundleRepository.get(id);
    }

    public boolean modifyProduct(BundleDetails product) {
        return false;
    }

    public boolean removeProduct(String id) {
        return false;
    }

    public boolean addProduct(BundleDetails product) {
        return false;
    }

    public List<Item> getCompanyProducts(String companyId) {
        return null;
    }
}
