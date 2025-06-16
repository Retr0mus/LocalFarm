package com.github.countrybros.application.product;

import com.github.countrybros.application.acceptancesubmission.IAcceptanceSubmissionService;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.infrastructure.product.IItemDetailsRepository;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import com.github.countrybros.model.acceptancesubmission.AddProductAcceptanceSubmission;
import com.github.countrybros.model.acceptancesubmission.EditProductAcceptanceSubmission;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.ItemDetails;
import com.github.countrybros.model.product.ItemDetailsStatus;
import com.github.countrybros.web.acceptancesubmission.request.AddProductAcceptanceSubmissionRequest;
import com.github.countrybros.web.acceptancesubmission.request.EditProductAcceptanceSubmissionRequest;
import com.github.countrybros.web.product.requests.AddItemDetailsRequest;
import com.github.countrybros.web.product.requests.EditItemDetailsRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Concrete class of ItemDetails manager.
 */
@Service
public class ItemDetailsService implements IItemDetailsService {

    private final IItemDetailsRepository itemDetailsRepository;
    private final ICompanyService companyService;
    private final IAcceptanceSubmissionService acceptanceSubmissionService;

    public ItemDetailsService(IItemDetailsRepository repository, ICompanyService companyService,
                              IAcceptanceSubmissionService acceptanceSubmissionService) {

        this.itemDetailsRepository = repository;
        this.companyService = companyService;
        this.acceptanceSubmissionService = acceptanceSubmissionService;
    }

    @Override
    public ItemDetails requestToAddItemDetails(AddItemDetailsRequest request) {

        ItemDetailsBuilderFactory factory = new ItemDetailsBuilderFactory();
        ItemDetailsBuilderDirector director = new ItemDetailsBuilderDirector(companyService, factory, this);

        ItemDetails itemDetails = itemDetailsRepository.save(director.createItemDetails(request));

        AddProductAcceptanceSubmissionRequest requestToAdd = new AddProductAcceptanceSubmissionRequest();
        requestToAdd.setItemDetailsId(itemDetails.getId());
        requestToAdd.setType("addProduct");
        requestToAdd.setSenderId(request.senderId);

        acceptanceSubmissionService.addAcceptanceSubmission(requestToAdd);

        return itemDetails;
    }

    @Override
    public void deleteItemDetails(int itemDetailsId) {

        if (!itemDetailsRepository.existsById(itemDetailsId))
            throw new NotFoundInRepositoryException("Item details not found");

        itemDetailsRepository.deleteById(itemDetailsId);
    }

    @Override
    public void acceptChanges(int acceptanceSubmissionId) {

        AcceptanceSubmission submission = acceptanceSubmissionService.getAcceptanceSubmission(acceptanceSubmissionId);

        if (submission instanceof AddProductAcceptanceSubmission sub)
            acceptItemDetails(sub.getItemDetailsId());

        else if (submission instanceof EditProductAcceptanceSubmission sub)
            editItem(sub.getProductToEditId(), sub.getProductChangeId());

        else
            throw new ImpossibleRequestException("Unsupported submission type");

        acceptanceSubmissionService.onAcceptance(acceptanceSubmissionId);
    }

    /**
     * Accept an item that is under review.
     *
     * @param itemDetailsId The itemDetails ID.
     */
    private void acceptItemDetails(int itemDetailsId) {

        ItemDetails itemDetails = getItemDetails(itemDetailsId);

        //TODO: implement
        //if (itemDetails.getStatus() != ItemDetailsStatus.underReview)
            //throw new ImpossibleRequestException("Item details not under review");

        itemDetails.setStatus(ItemDetailsStatus.available);
        itemDetailsRepository.save(itemDetails);
    }

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
    private void editItem(int existingItemDetailsId, int changedItemDetailsId) {

        ItemDetails existingItemDetails = getItemDetails(existingItemDetailsId);
        ItemDetails changedItemDetails = getItemDetails(changedItemDetailsId);

        if (!existingItemDetails.getClass().equals(changedItemDetails.getClass()))
            throw new ImpossibleRequestException("ItemDetails type not compatible");

        if (existingItemDetails.equals(changedItemDetails))
            throw new RequestAlreadySatisfiedException("Invalid ItemDetails edit request: changes already applied");

        if (!changedItemDetails.getStatus().equals(ItemDetailsStatus.underReview))
            throw new ImpossibleRequestException("Changes can't be applied if they're not under review");

        if (!existingItemDetails.getStatus().equals(ItemDetailsStatus.available)
                && !existingItemDetails.getStatus().equals(ItemDetailsStatus.outOfStock))
            throw new ImpossibleRequestException("The ItemDetails to update has incompatible status");

        BeanUtils.copyProperties(changedItemDetails, existingItemDetails);
        existingItemDetails.setStatus(ItemDetailsStatus.available);
        existingItemDetails.setVisibleByPublic(true);

        //do not reverse this two lines
        deleteItemDetails(changedItemDetailsId);
        itemDetailsRepository.save(existingItemDetails);
    }

    @Override
    public void requestChanges(EditItemDetailsRequest request) {

        ItemDetails changes = requestToAddItemDetails(request.changesToItemDetails);

        EditProductAcceptanceSubmissionRequest requestToAdd = new EditProductAcceptanceSubmissionRequest();
        requestToAdd.setProductToEditId(request.itemId);
        requestToAdd.setProductChangeId(changes.getId());
        requestToAdd.setType("editProduct");
        requestToAdd.setSenderId(request.senderId);

        acceptanceSubmissionService.addAcceptanceSubmission(requestToAdd);
    }

    @Override
    public void setStatus(ItemDetailsStatus newStatus, int itemDetailsId) {

        ItemDetails itemDetails = getItemDetails(itemDetailsId);

        if (newStatus.equals(ItemDetailsStatus.available)) {
            if (itemDetails.getStatus().equals(ItemDetailsStatus.awaitingReview))
                throw new ImpossibleRequestException("Cannot change the status of an ItemDetails that is awaiting review");

            itemDetails.setVisibleByPublic(true);
        }

        if (newStatus.equals(ItemDetailsStatus.outOfStock) &&
                !itemDetails.getStatus().equals(ItemDetailsStatus.available))
            throw new ImpossibleRequestException("Cannot update to out of stock if not available");

        if (newStatus.equals(ItemDetailsStatus.underReview)
                && !itemDetails.getStatus().equals(ItemDetailsStatus.awaitingReview))
            throw new ImpossibleRequestException("Cannot update to under review if not awaiting review");

        if (newStatus.equals(ItemDetailsStatus.awaitingReview))
            throw new ImpossibleRequestException("Cannot update to awaiting review");

        itemDetails.setStatus(newStatus);
        itemDetailsRepository.save(itemDetails);
    }

    @Override
    public ItemDetails getItemDetails(int itemDetailsId) {

        ItemDetails itemDetails = itemDetailsRepository.findById(itemDetailsId).orElse(null);

        if (itemDetails == null)
            throw new NotFoundInRepositoryException("Item details not found");

        return itemDetails;
    }

    @Override
    public List<ItemDetails> getCompanyItemDetails(int companyId) {

        return itemDetailsRepository.findAllByProducer_Id(companyId);
    }
}
