package com.github.countrybros.application.product;

import com.github.countrybros.model.product.ItemDetails;
import com.github.countrybros.model.user.Company;

/**
 * Generic behaviour of an ItemDetailsBuilder
 */
public interface IItemDetailsBuilder {

    /**
     * resets the building, prepare for another object to being created.
     */
    void reset();

    /**
     * Returns the built element.
     *
     * @return the element
     */
    ItemDetails getResult();

    void setName(String productName);

    void setDescription(String productDescription);

        void setProducer(Company producer);
}
