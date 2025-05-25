package com.github.countrybros.application;

import com.github.countrybros.model.BundleDetails;

/**
 * Provides to define all the services needed for management of @BundleDetails
 */
public interface IBundleDetailsService {

    /**
     * Gets the selected bundle.
     *
     * @param id the ID
     * @return The bundle, if exixts
     */
    public BundleDetails getBundle(int id);

    /**
     * Add a bundle in the repository.
     *
     * @param bundle the bundle to insert.
     *
     * @return true if success.
     */
    public boolean addBundle(BundleDetails bundle);
}
