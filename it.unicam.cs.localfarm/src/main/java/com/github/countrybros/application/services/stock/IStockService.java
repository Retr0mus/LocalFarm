package com.github.countrybros.application.services.stock;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.model.item.Item;
import com.github.countrybros.model.stock.Stock;
import com.github.countrybros.model.company.Company;

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
    Stock add(Stock request);

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
     * @param stockId   The item ID.
     * @param sellerId  The seller ID.
     * @param price     The price to put.
     */
    void setPrice(int stockId, int sellerId, double price);

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

    /**
     * Deletes all stocks of a company.
     *
     * @param companyId     The give company's id.
     */
    void deleteAllCompanyStocks(int companyId);
}
