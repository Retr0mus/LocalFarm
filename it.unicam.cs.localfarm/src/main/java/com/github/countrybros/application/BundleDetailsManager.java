package com.github.countrybros.application;

import com.github.countrybros.model.product.BundleDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the BundleDetails.
 */
public class BundleDetailsManager {
    private Map<Integer, BundleDetails> bundleRepository = new HashMap<>();

    public BundleDetails getBundleById(int id) {
        return bundleRepository.get(id);
    }

}
