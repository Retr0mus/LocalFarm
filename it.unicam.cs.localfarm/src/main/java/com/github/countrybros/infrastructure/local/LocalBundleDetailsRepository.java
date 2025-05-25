package com.github.countrybros.infrastructure.local;

import com.github.countrybros.infrastructure.IBundleDetailsRepository;
import com.github.countrybros.model.BundleDetails;

import java.util.HashMap;

/**
 * Local repo of @BundleDetails
 */
public class LocalBundleDetailsRepository implements IBundleDetailsRepository {

    private HashMap<Integer, BundleDetails> repository;

    @Override
    public BundleDetails get(int id) {

        return repository.get(id);
    }

    @Override
    public void delete(int id) {

        repository.remove(id);
    }

    @Override
    public void save(BundleDetails productDetails) {

        repository.put(productDetails.getId(), productDetails);
    }

    @Override
    public boolean exists(int id) {

        return repository.containsKey(id);
    }
}
