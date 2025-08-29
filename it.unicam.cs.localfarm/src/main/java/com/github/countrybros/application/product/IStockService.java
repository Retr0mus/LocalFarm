package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.Stock;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.product.requests.AddStockRequest;

import java.util.List;

/**
 * Defines the responsibilities to manage stocks
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
     * @param sellerId ID of the selling company.
     *
     * @throws NotFoundInRepositoryException if the item doesn't exist
     */
    void addQuantityToStock(int itemId, int quantity, int sellerId);

    /**
     * Subtract the specified quantity to an @Item.
     *
     * @param itemId ID of the item.
     * @param quantity Quantity.
     * @param sellerId ID of the selling company.
     *
     * @throws ImpossibleRequestException if the subtraction is negative.
     */
    void removeQuantityToStock(int itemId, int quantity, int sellerId);

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
    Stock getStock(int itemId);

    /**
     * Returns the list of stocks correlated to a company.
     *
     * @param companyId The company ID.
     * @return the list of stocks of that company.
     */
    List<Stock> getStocksBySeller(int companyId);

    /**
     * Returns the list of the stocks related to a certain item.
     * 
     * @param itemId    The item ID.
     * @return          The list of the stocks regarding that item.
     */
    List<Stock> getStocksByItem(int itemId);

    /**
     * Gets the stock relative to the given item and seller.
     *
     * @param item      the reference item.
     * @param seller    the company that supposedly sells that item.
     * @return          the associated stock.
     */
    Stock getStockByItemAndSeller(Item item, Company seller);
}
