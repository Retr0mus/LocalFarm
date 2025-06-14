package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.model.product.ItemDetails;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.model.product.ItemDetailsStatus;
import com.github.countrybros.web.product.requests.AddItemDetailsRequest;
import com.github.countrybros.web.product.requests.EditItemDetailsRequest;

import java.util.List;

/**
 * Generic ItemDetails manager, use the abstract class to accept request that have shared behaviour.
 */
public interface IItemDetailsService {

    /**
     * Create a new ItemDetails.
     *
     * @param request The new ItemDetails request.
     * @return
     */
    ItemDetails addItemDetails(AddItemDetailsRequest request);

    /**
     * Deletes an existing ItemDetails.
     *
     * @param itemDetailsId The ItemDetails ID.
     *
     * @throws NotFoundInRepositoryException if there is no ItemDetails with that id.
     */
    void deleteItemDetails(int itemDetailsId);


    /**
     * Sets all the base details of an ItemDetails equal to another one,
     * the first ItemDetails should be public, the other should be under review and
     * will be deleted.
     *
     * @param existingItemDetailsId The ID of the ItemDetails that will be relaced.
     * @param changedItemDetailsId The new ItemDetails ID.
     *
     * @throws RequestAlreadySatisfiedException if there are no changes.
     * @throws ImpossibleRequestException if the subtypes are incompatible.
     */
    void acceptChanges(int existingItemDetailsId, int changedItemDetailsId);

    /**
     * Create a new ItemDetails with the changes regards a certain existing one.
     * This will generate a request that a Curator will review.
     *
     * @param request The edit request
     */
    void editItemDetails(EditItemDetailsRequest request);

    /**
     * Changes the status of an ItemDetails according to the previous one.
     *
     * @param itemDetailsStatus The new status.
     * @param itemDetailsId The itemDetails ID.
     *
     * @throws ImpossibleRequestException if the chosen status is not valid.
     */
    void setStatus(ItemDetailsStatus itemDetailsStatus, int itemDetailsId);

    /**
     * Gets the specified ItemDetails.
     *
     * @param itemDetailsId The ItemDetails ID.
     *
     * @throws NotFoundInRepositoryException if it's not present.
     */
    ItemDetails getItemDetails(int itemDetailsId);

    /**
     * Returns all the ItemDetails of a specified Company.
     *
     * @param companyId ID of the company.
     */
    List<ItemDetails> getCompanyItemDetails(int companyId);
}
