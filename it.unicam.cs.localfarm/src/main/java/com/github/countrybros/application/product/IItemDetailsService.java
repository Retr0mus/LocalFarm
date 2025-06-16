package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
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
     * Create a new ItemDetails that is not public, waiting for a curator to review
     *
     * @param request The new ItemDetails request.
     *
     * @return The item details.
     */
    ItemDetails requestToAddItemDetails(AddItemDetailsRequest request);

    /**
     * Deletes an existing ItemDetails.
     *
     * @param itemDetailsId The ItemDetails ID.
     *
     * @throws NotFoundInRepositoryException if there is no ItemDetails with that id.
     */
    void deleteItemDetails(int itemDetailsId);


    /**
     * Accepts a submission, making the necessary changes.
     *
     * @param acceptanceSubmissionId The submission ID
     *
     * @throws ImpossibleRequestException if the type of submission is not valid.
     */
    void acceptChanges(int acceptanceSubmissionId);

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

    /**
     * Create a new ItemDetails that contains the changes.
     *
     * @param request The request
     */
    void requestChanges(EditItemDetailsRequest request);
}
