package com.github.countrybros.application.services.stock;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repositories.product.IStockRepository;
import com.github.countrybros.model.item.Item;
import com.github.countrybros.model.stock.Stock;
import com.github.countrybros.model.company.Company;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.Math.max;

/**
 * Service that performs all the tasks related to the management of the item.
 */
@Service
public class StockService implements IStockService {

    private final IStockRepository stockRepository;

    public StockService(IStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Creates a stock.
     *
     * @param stock The addItem request.
     */
    @Override
    public Stock add(Stock stock) {
        if(stockRepository.findByItemAndSeller(stock.getItem(), stock.getSeller()) != null) {
            throw new ImpossibleRequestException("Stock already exists");
        };

        return stockRepository.save(stock);
    }

    /**
     * Adds the specified quantity to an @Item.
     *
     * @param stockId   ID of the stock.
     * @param quantity Quantity.
     * @param sellerId ID of the selling company.
     * @throws NotFoundInRepositoryException if the item doesn't exist
     */
    public void addQuantityToStock(int stockId, int quantity, int sellerId) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new NotFoundInRepositoryException("Stock not found"));

        stock.setQty(stock.getQty() + quantity);
        stockRepository.save(stock);
    }

    /**
     * Subtract the specified quantity to an @Item.
     *
     * @param stockId  ID of the item.
     * @param quantity Quantity.
     * @throws ImpossibleRequestException if the subtraction is negative.
     */
    @Override
    public void removeQuantityToStock(int stockId, int quantity, int sellerId) {
        Stock stock = getStock(stockId);

        if (stock == null)
            throw new ImpossibleRequestException("Stock does not exist");

        if (stock.getSeller().getId() != sellerId)
            throw new ImpossibleRequestException("The requested stock isn't owned by the seller");

        if (quantity <= 0)
            throw new ImpossibleRequestException("Cannot remove negative or null quantity");

        stock.setQty(max(stock.getQty() - quantity, 0));

        this.stockRepository.save(stock);
    }

    /**
     * Sets a specific price for an item.
     *
     * @param stockId   The item ID.
     * @param sellerId  The seller ID.
     * @param newPrice     The price to put.
     */
    @Override
    public void setPrice(int stockId, int sellerId, double newPrice) {
        Stock stock = getStock(stockId);

        if(stock.getSeller().getId() != sellerId)
            throw new ImpossibleRequestException("The requested stock isn't owned by the seller");

        if(new BigDecimal(newPrice).compareTo(BigDecimal.ZERO) <= 0)
            throw new ImpossibleRequestException("The new price cannot be negative or null");

        stock.setPrice(newPrice);
        stockRepository.save(stock);
    }

    /**
     * Returns the selected item, if exists.
     *
     * @param stockId The item ID.
     * @return The Item.
     * @throws NotFoundInRepositoryException if the item doesn't exist.
     */
    @Override
    public Stock getStock(int stockId) {
        return stockRepository.findById(stockId).orElseThrow(() -> new NotFoundInRepositoryException("Stock not found"));
    }

    /**
     * Returns the list of items correlated to a company.
     *
     * @param companyId The company ID.
     * @return the list of items of that company.
     */
    @Override
    public List<Stock> getStocksBySeller(int companyId) {
        return stockRepository.findAllBySeller_Id(companyId);
    }

    /**
     * Returns the list of the stocks related to a certain item.
     *
     * @param itemId The item ID.
     * @return The list of the stocks regarding that item.
     */
    @Override
    public List<Stock> getStocksByItem(int itemId) {
        return stockRepository.findAllByItem_Id(itemId);
    }

    public Stock getStockByItemAndSeller(Item item, Company seller) {
        return stockRepository.findByItemAndSeller(item, seller);
    }

    /**
     * Deletes all stocks of a company.
     *
     * @param companyId The give company's id.
     */
    @Override
    public void deleteAllCompanyStocks(int companyId) {
        List<Stock> stocks = stockRepository.findAllBySeller_Id(companyId);
        stockRepository.deleteAll(stocks);
    }
}