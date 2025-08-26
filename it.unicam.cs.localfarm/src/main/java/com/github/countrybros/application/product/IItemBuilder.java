package com.github.countrybros.application.product;

import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.user.Company;

/**
 * Generic behaviour of an ItemDetailsBuilder
 */
public interface IItemBuilder {

    /**
     * resets the building, prepare for another object to being created.
     */
    void reset();

    /**
     * Returns the built element.
     *
     * @return the element
     */
    Item getResult();

    void setName(String productName);

    void setDescription(String productDescription);

        void setProducer(Company producer);
}
