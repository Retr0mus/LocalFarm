package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.model.product.ItemStatus;

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
     * Accepts a submission, making the necessary changes.
     *
     * @param acceptanceSubmissionId The submission ID
     * @throws ImpossibleRequestException if the type of submission is not valid.
     */
    void acceptChanges(int acceptanceSubmissionId);

    /**
     * Changes the status of an ItemDetails according to the previous one.
     *
     * @param itemDetailsStatus The new status.
     * @param itemDetailsId     The itemDetails ID.
     * @throws ImpossibleRequestException if the chosen status is not valid.
     */
    void setStatus(ItemStatus itemDetailsStatus, int itemDetailsId);

    /**
     * Gets the specified ItemDetails.
     *
     * @param itemId The ItemDetails ID.
     * @throws NotFoundInRepositoryException if it's not present.
     */
    Item getItem(int itemId);

    List<Item> getAvailableItems();

    /**
     * Returns all the ItemDetails of a specified Company.
     *
     * @param companyId ID of the company.
     */
    List<Item> getCompanyItemDetails(int companyId);

    
}
