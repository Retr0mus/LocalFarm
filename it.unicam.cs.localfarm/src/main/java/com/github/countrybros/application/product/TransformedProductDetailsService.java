package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.ITransformedProductDetailsRepository;
import com.github.countrybros.infrastructure.local.LocalTransformedProductDetailsRepository;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.TransformedProductDetails;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the TransformedProductDetails.
 */
public class TransformedProductDetailsService implements ITransformedProductDetailsService {

    /**
     * Repository of @TransformedProductDetails
     */
    private final ITransformedProductDetailsRepository TransformedProductRepository = new LocalTransformedProductDetailsRepository();


    /**
     * Returns the specified element.
     *
     * @param id the ID
     *
     * @return the requested element.
     */
    public TransformedProductDetails getProduct(int id) {
        return TransformedProductRepository.get(id);
    }

    /**
     * Updates the product, if present.
     *
     * @param product The product to update.
     *
     * @throws NotFoundInRepositoryException if the element is not present
     */
    public void modifyProduct(TransformedProductDetails product) {

        if (!this.TransformedProductRepository.exists(product.getId()))
            throw new NotFoundInRepositoryException("Product not found");

        this.TransformedProductRepository.save(product);
    }

    /**
     * Deletes the selected element.
     *
     * @param id the identifier.
     *
     * @return true if the element existed.
     */
    public boolean removeProduct(int id) {

        if (!this.TransformedProductRepository.exists(id))
            return false;

        this.TransformedProductRepository.delete(id);
        return true;
    }

    /**
     * Add a new element.
     *
     * @param product the new element.
     *
     * @return true if the element was not present before.
     */
    public boolean addProduct(TransformedProductDetails product) {

        if (this.TransformedProductRepository.exists(product.getId()))
            return false;

        this.TransformedProductRepository.save(product);
        return true;
    }

    public List<Item> getCompanyProducts(String companyId) {
        return null;
    }
}
