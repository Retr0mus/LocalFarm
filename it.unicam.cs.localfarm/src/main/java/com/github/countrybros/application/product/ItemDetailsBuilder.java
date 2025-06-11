package com.github.countrybros.application.product;

import com.github.countrybros.application.user.CompanyService;
import com.github.countrybros.model.product.ItemDetailsStatus;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.product.ItemDetails;

/**
 * Creates an @ItemDetails
 */
public abstract class ItemDetailsBuilder<T extends ItemDetails> implements IItemDetailsBuilder {

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

    @Override
    public void reset() {

        product = this.createInstance();
    }

    @Override
    public ItemDetails build() {

        //TODO: impostare a awaitingReview, o cambiare gli stati
        product.setVisibleByPublic(false);
        product.setStatus(ItemDetailsStatus.awaitingReview);
        return product;
    }

    @Override
    public void setName(String productName) {

        product.setName(productName);
    }

    @Override
    public void setDescription(String productDescription) {

        product.setDescription(productDescription);
    }

    @Override
    public void setProducer(Company producer) {

        product.setProducer(producer);
    }
}
