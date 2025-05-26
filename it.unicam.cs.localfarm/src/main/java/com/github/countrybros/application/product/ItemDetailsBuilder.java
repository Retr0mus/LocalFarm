package com.github.countrybros.application.product;

import com.github.countrybros.application.user.CompanyService;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.product.ItemDetails;

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
    protected CompanyService companyManager;

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
