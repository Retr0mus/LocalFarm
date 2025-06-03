package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.product.SimpleProductDetailsRepository;
import com.github.countrybros.model.product.SimpleProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service that performs all the tasks related to the management of the SimpleProductDetails.
 */
@Service
public class SimpleProductDetailsService {

    /**
     * Repository of @SimpleProductDetails
     */
    private final SimpleProductDetailsRepository simpleProductRepository;

    @Autowired
    public SimpleProductDetailsService(SimpleProductDetailsRepository simpleProductRepository) {
        this.simpleProductRepository = simpleProductRepository;
    }


    /**
     * Returns the specified element.
     *
     * @param id the ID
     *
     * @return the requested element.
     */
    public SimpleProductDetails getProduct(int id) {
        return (SimpleProductDetails) simpleProductRepository.findById(id).orElse(null);
    }

    /**
     * Updates the product, if present.
     *
     * @param product The product to update.
     *
     * @throws NotFoundInRepositoryException if the element is not present
     */
    public void modifyProduct(SimpleProductDetails product) {

        if (!this.simpleProductRepository.existsById(product.getId()))
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

        if (!this.simpleProductRepository.existsById(id))
            return false;

        this.simpleProductRepository.deleteById(id);
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

        if (this.simpleProductRepository.existsById(product.getId()))
            return false;

        this.simpleProductRepository.save(product);
        return true;
    }

}
