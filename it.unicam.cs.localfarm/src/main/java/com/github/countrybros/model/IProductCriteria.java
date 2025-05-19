package com.github.countrybros.model;

/**
 * Criteria interface for product filtering.
 */
public interface IProductCriteria {
    /**
     * Checks if the given product satisfies a specific criterion.
     * @param product The simple product details to check.
     * @return True if the product satisfies the criterion, false otherwise.
     */
    boolean isVerified(SimpleProductDetails product);
}
