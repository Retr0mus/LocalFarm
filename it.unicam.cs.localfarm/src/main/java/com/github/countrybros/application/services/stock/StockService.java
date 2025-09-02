package com.github.countrybros.application.services.stock;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repositories.product.IStockRepository;
import com.github.countrybros.model.item.Item;
import com.github.countrybros.model.stock.Stock;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.application.models.requests.item.AddStockRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.max;

/**
 * Service that performs all the tasks related to the management of the item.
 */
@Service
public class StockService implements IStockService {

    private final IStockRepository IStockRepository;

    public StockService(IStockRepository IStockRepository) {
        this.IStockRepository = IStockRepository;
    }

    /**
     * Inserts an Item in the repo.
     *
     * @param request The addItem request.
     */
    @Override
    public Stock addItem(AddStockRequest request) {
        /*
        Company seller = companyService.getCompany(request.sellerId);
        Item item = itemDetailsService.getItemDetails(request.itemDetailsId);

        Stock stock = new Stock();
        //item.setSeller(seller);
        stock.setItemDetails(item);
        stock.setQty(request.qty);
        stock.setPrice(request.price);

        return stockRepository.save(stock);
        */
        return null;
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
        Stock stock = IStockRepository.findById(stockId).orElseThrow(() -> new NotFoundInRepositoryException("Stock not found"));

        stock.setQty(stock.getQty() + quantity);
        IStockRepository.save(stock);
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

        this.IStockRepository.save(stock);
    }

    /**
     * Sets a specific price for an item.
     *
     * @param itemId The item ID
     * @param price  The price to put.
     */
    @Override
    public void setPrice(int itemId, double price) {

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
        return IStockRepository.findById(stockId).orElse(null);
    }

    /**
     * Returns the list of items correlated to a company.
     *
     * @param companyId The company ID.
     * @return the list of items of that company.
     */
    @Override
    public List<Stock> getStocksBySeller(int companyId) {
        return IStockRepository.findAllBySeller_Id(companyId);
    }

    /**
     * Returns the list of the stocks related to a certain item.
     *
     * @param itemId The item ID.
     * @return The list of the stocks regarding that item.
     */
    @Override
    public List<Stock> getStocksByItem(int itemId) {
        return IStockRepository.findAllByItem_Id(itemId);
    }

    public Stock getStockByItemAndSeller(Item item, Company seller) {
        return IStockRepository.findByItemAndSeller(item, seller);
    }
}