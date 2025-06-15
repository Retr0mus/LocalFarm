package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.user.CompanyService;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.product.ItemRepository;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.ItemDetails;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.product.requests.AddItemRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that performs all the tasks related to the management of the item.
 */
@Service
public class ItemService implements IItemService {
  
    private final CompanyService companyService;
    private final ItemRepository itemRepository;
    private final IItemDetailsService itemDetailsService;

    public ItemService(CompanyService companyService, ItemRepository itemRepository,
                       IItemDetailsService itemDetailsService) {

        this.companyService = companyService;
        this.itemRepository = itemRepository;
        this.itemDetailsService = itemDetailsService;
    }

    //TODO Recognise la richiesta di accetazione e la modifca di essa

    public Item addItem(AddItemRequest request) {


        Company seller = companyService.getCompany(request.sellerId);
        ItemDetails itemDetails = itemDetailsService.getItemDetails(request.itemDetailsId);

        Item item = new Item();
        //item.setSeller(seller);
        item.setItemDetails(itemDetails);
        item.setQty(request.qty);
        item.setPrice(request.price);

        return itemRepository.save(item);
    }

    /**
     * Adds the specified quantity to an @Item.
     *
     * @param itemId ID of the item.
     * @param quantity Quantity.
     */
    public void addQuantityToItem(int itemId, int quantity) {

        Item item = getItem(itemId);

        item.setQty(item.getQty() + quantity);

        this.itemRepository.save(item);
    }

    /**
     * Subtract the specified quantity to an @Item.
     *
     * @param itemId ID of the item.
     * @param quantity Quantity.
     */
    public void removeQuantityToItem(int itemId, int quantity) {

        Item item = getItem(itemId);

        if (item.getQty() < quantity)
            throw new ImpossibleRequestException("current quantity is too low");

        item.setQty(item.getQty() - quantity);

        this.itemRepository.save(item);
    }

    /**
     * @inheritDoc
     */
    public void setPrice(int itemId, float price) {

        Item item = getItem(itemId);
        item.setPrice(price);
        itemRepository.save(item);
    }

    /**
     * @inheritDoc
     */
    public Item getItem(int itemId) {

        Item item = this.itemRepository.findById(itemId).orElse(null);

        if (item == null)
            throw new NotFoundInRepositoryException("Item not found");

        return item;
    }

    /**
     * @inheritDoc
     */
    public List<Item> getItemsBySeller(int companyId) {

        Company company = companyService.getCompany(companyId);

        //TODO: continue when Company is not transient
        return new ArrayList<>();
    }
}
