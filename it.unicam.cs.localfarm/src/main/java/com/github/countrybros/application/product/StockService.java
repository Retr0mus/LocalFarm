package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.user.CompanyService;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.infrastructure.product.ItemRepository;
import com.github.countrybros.model.product.Stock;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.product.requests.AddStockRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that performs all the tasks related to the management of the item.
 */
@Service
public class StockService implements IStockService {
  
    private final ICompanyService companyService;
    private final ItemRepository itemRepository;
    private final IItemService itemDetailsService;

    public StockService(CompanyService companyService, ItemRepository itemRepository,
                        IItemService itemDetailsService) {

        this.companyService = companyService;
        this.itemRepository = itemRepository;
        this.itemDetailsService = itemDetailsService;
    }

    //TODO Recognise la richiesta di accetazione e la modifca di essa

    public Stock addItem(AddStockRequest request) {


        Company seller = companyService.getCompany(request.sellerId);
        Item item = itemDetailsService.getItemDetails(request.itemDetailsId);

        Stock stock = new Stock();
        //item.setSeller(seller);
        stock.setItemDetails(item);
        stock.setQty(request.qty);
        stock.setPrice(request.price);

        return itemRepository.save(stock);
    }

    /**
     * Adds the specified quantity to an @Item.
     *
     * @param itemId ID of the item.
     * @param quantity Quantity.
     */
    public void addQuantityToItem(int itemId, int quantity) {

        Stock stock = getItem(itemId);

        stock.setQty(stock.getQty() + quantity);

        this.itemRepository.save(stock);
    }

    /**
     * Subtract the specified quantity to an @Item.
     *
     * @param itemId ID of the item.
     * @param quantity Quantity.
     */
    public void removeQuantityToItem(int itemId, int quantity) {

        Stock stock = getItem(itemId);

        if (stock.getQty() < quantity)
            throw new ImpossibleRequestException("current quantity is too low");

        stock.setQty(stock.getQty() - quantity);

        this.itemRepository.save(stock);
    }

    /**
     * @inheritDoc
     */
    public void setPrice(int itemId, double price) {

        Stock stock = getItem(itemId);
        stock.setPrice(price);
        itemRepository.save(stock);
    }

    /**
     * @inheritDoc
     */
    public Stock getItem(int itemId) {

        Stock stock = this.itemRepository.findById(itemId).orElse(null);

        if (stock == null)
            throw new NotFoundInRepositoryException("Item not found");

        return stock;
    }

    /**
     * @inheritDoc
     */
    public List<Stock> getItemsBySeller(int companyId) {

        Company company = companyService.getCompany(companyId);

        //TODO: continue when Company is not transient
        return new ArrayList<>();
    }
}
