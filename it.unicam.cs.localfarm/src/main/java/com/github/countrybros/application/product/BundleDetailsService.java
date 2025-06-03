package com.github.countrybros.application.product;

import com.github.countrybros.infrastructure.product.BundleDetailsRepository;
import com.github.countrybros.model.product.BundleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the @BundleDetails.
 */
@Service
public class BundleDetailsService {

    private final BundleDetailsRepository bundleDetailsRepository;

    @Autowired
    public BundleDetailsService(BundleDetailsRepository bundleDetailsRepository) {
        this.bundleDetailsRepository = bundleDetailsRepository;
    }

    //TODO: rewrite all the service

    public BundleDetails getBundle(int id) {

        return (BundleDetails) bundleDetailsRepository.findById(id).orElse(null);
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
