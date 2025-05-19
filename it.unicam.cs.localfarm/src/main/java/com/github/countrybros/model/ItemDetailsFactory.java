package com.github.countrybros.model;


/**
 * Factory interface for item details.
 */
public interface ItemDetailsFactory {

    ItemDetails create(String name, String details, Company company);
}
