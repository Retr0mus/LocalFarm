package com.github.countrybros.model;

import java.util.HashMap;

/**
 * Factory for BundleDetails.
 */
public class BundleDetailsFactory implements ItemDetailsFactory{

    @Override
    public BundleDetails create(String name, String description, Company company) {

        BundleDetails bundle = new BundleDetails(name, description, company);

        HashMap<Item, Integer> bundleItems = new HashMap<Item, Integer>();
        bundle.setItemsMap(bundleItems);

        return bundle;
    }
}
