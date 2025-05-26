package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.ISimpleProductDetailsRepository;
import com.github.countrybros.infrastructure.local.LocalSimpleProductDetailsRepository;
import com.github.countrybros.model.product.SimpleProductDetails;


/**
 * Service that performs all the tasks related to the management of the SimpleProductDetails.
 */
public class SimpleProductDetailsService {

    /**
     * Repository of @SimpleProductDetails
     */
    private final ISimpleProductDetailsRepository simpleProductRepository = new LocalSimpleProductDetailsRepository();


    /**
     * Returns the specified element.
     *
     * @param id the ID
     *
     * @return the requested element.
     */
    public SimpleProductDetails getProduct(int id) {
        return simpleProductRepository.get(id);
    }

    /**
     * Updates the product, if present.
     *
     * @param product The product to update.
     *
     * @throws NotFoundInRepositoryException if the element is not present
     */
    public void modifyProduct(SimpleProductDetails product) {

        if (!this.simpleProductRepository.exists(product.getId()))
            throw new NotFoundInRepositoryException("Product not found");

        this.simpleProductRepository.save(product);
    }

    /**
     * Deletes the selected element.
     *
     * @param id the identifier.
     *
     * @return true if the element existed.
     */
    public boolean removeProduct(int id) {

        if (!this.simpleProductRepository.exists(id))
            return false;

        this.simpleProductRepository.delete(id);
        return true;
    }

    /**
     * Add a new element.
     *
     * @param product the new element.
     *
     * @return true if the element was not present before.
     */
    public boolean addProduct(SimpleProductDetails product) {

        if (this.simpleProductRepository.exists(product.getId()))
            return false;

        this.simpleProductRepository.save(product);
        return true;
    }

}
