package com.github.countrybros.application.product;


import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.product.SimpleProductDetails;

/**
 * Defines the services relative to @SimpleProductDetails
 */
public interface ISimpleProductDetailsService {

    /**
     * Returns the specified element.
     *
     * @param id the ID
     *
     * @return the requested element.
     */
    public SimpleProductDetails getProduct(int id);

    /**
     * Updates the product, if present.
     *
     * @param product The product to update.
     *
     * @throws NotFoundInRepositoryException if the element is not present
     */
    public void modifyProduct(SimpleProductDetails product);

    /**
     * Deletes the selected element.
     *
     * @param id the identifier.
     *
     * @return true if the element existed.
     */
    public boolean removeProduct(int id);

    /**
     * Add a new element.
     *
     * @param product the new element.
     *
     * @return true if the element was not present before.
     */
    public boolean addProduct(SimpleProductDetails product);
}
