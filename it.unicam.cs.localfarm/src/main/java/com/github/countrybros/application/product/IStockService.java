package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.model.product.Stock;
import com.github.countrybros.web.product.requests.AddStockRequest;

import java.util.List;

/**
 * Defines the responsibilities to manage Items
 */
public interface IStockService {

    /**
     * Inserts an Item in the repo.
     *
     * @param request The addItem request.
     */
    Stock addItem(AddStockRequest request);

    /**
     * Adds the specified quantity to an @Item.
     *
     * @param itemId ID of the item.
     * @param quantity Quantity.
     *
     * @throws NotFoundInRepositoryException if the item doesn't exist
     */
    void addQuantityToItem(int itemId, int quantity);

    /**
     * Subtract the specified quantity to an @Item.
     *
     * @param itemId ID of the item.
     * @param quantity Quantity.
     *
     * @throws ImpossibleRequestException if the subtraction is negative.
     */
    void removeQuantityToItem(int itemId, int quantity);

    /**
     * Sets a specific price for an item.
     *
     * @param itemId The item ID
     * @param price The price to put.
     */
    void setPrice(int itemId, double price);

    /**
     * Returns the selected item, if exists.
     *
     * @param itemId The item ID.
     * @return The Item.
     *
     * @throws NotFoundInRepositoryException if the item doesn't exist.
     */
    Stock getItem(int itemId);

    /**
     * Returns the list of items correlated to a company.
     *
     * @param companyId The company ID.
     * @return the list of items of that company.
     */
    List<Stock> getItemsBySeller(int companyId);
}
