package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.ItemStatus;
import com.github.countrybros.web.product.requests.AddItemRequest;

import java.util.List;
import java.util.Objects;

/**
 * Generic Item manager, use the abstract class to accept request that have shared behaviour.
 */
public interface IItemService {

    /**
     * Adds an Item to the repository.
     *
     * @param item the item to add.
     */
    void addItem(Item item);

    /**
     * Deletes an existing ItemDetails.
     *
     * @param itemDetailsId The ItemDetails ID.
     * @throws NotFoundInRepositoryException if there is no ItemDetails with that id.
     */
    void deleteItemDetails(int itemDetailsId);

    /**
     * Changes the status of an ItemDetails according to the previous one.
     *
     * @param itemStatus The new status.
     * @param itemId     The itemDetails ID.
     * @throws ImpossibleRequestException if the chosen status is not valid.
     */
    void setStatus(ItemStatus itemStatus, int itemId);


    /**
     * Gets the specified ItemDetails.
     *
     * @param itemId The ItemDetails ID.
     * @throws NotFoundInRepositoryException if it's not present.
     */
    Item getItem(int itemId);

    List<Item> getCompanyItemDetails(int companyId);

    List<Item> getAvailableItems();

    /**
     * Returns all the ItemDetails of a specified Company.
     *
     * @param companyId ID of the company.
     */
    List<Item> getItemsBySeller(int companyId);
}
