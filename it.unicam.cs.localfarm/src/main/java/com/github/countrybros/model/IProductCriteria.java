package com.github.countrybros.model;

import com.github.countrybros.model.product.SimpleProduct;

/**
 * Criteria interface for product filtering.
 */
@Deprecated
public interface IProductCriteria {
    /**
     * Checks if the given product satisfies a specific criterion.
     * @param product The simple product details to check.
     * @return True if the product satisfies the criterion, false otherwise.
     */
    boolean isVerified(SimpleProduct product);
}
