package com.github.countrybros.application;

import com.github.countrybros.model.BundleDetails;

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

    /**
     * Add a bundle in the repository.
     *
     * @param bundle the bundle to insert.
     *
     * @return true if success.
     */
    public boolean addBundle(BundleDetails bundle) {

        bundleRepository.put(bundle.getId(), bundle);

        return true;
    }
}
