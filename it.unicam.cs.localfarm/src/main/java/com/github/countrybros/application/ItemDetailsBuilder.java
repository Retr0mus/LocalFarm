package com.github.countrybros.application;

import com.github.countrybros.model.Company;
import com.github.countrybros.model.ItemDetails;

/**
 * Creates an @ItemDetails
 */
public abstract class ItemDetailsBuilder<T extends ItemDetails> {

    /**
     * The Item to build.
     */
     T product;

    /**
     * service to request companies management
     */
    protected CompanyManager companyManager;

    /**
     * Creates the right instance.
     *
     * @return the instance of the correct subtype
     */
    protected abstract T createInstance();


    public ItemDetailsBuilder() {

        this.reset();
    }

    /**
     * resets teh building, prepare for another object to being created.
     */
    public void reset() {

        product = this.createInstance();
    }

    /**
     * Returns the built element.
     *
     * @return the element
     */
    public ItemDetails build() {

        return product;
    }

    public void setName(String productName) {

        product.setName(productName);
    }


    public void setDescription(String productDescription) {

        product.setDescription(productDescription);
    }

    public void setProducer(Company producer) {

        product.setProducer(producer);
    }
}
