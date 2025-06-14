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
    public void reset();

    /**
     * Returns the built element.
     *
     * @return the element
     */
    public ItemDetails build();

    public void setName(String productName);

    public void setDescription(String productDescription);

    public void setProducer(Company producer);
}
