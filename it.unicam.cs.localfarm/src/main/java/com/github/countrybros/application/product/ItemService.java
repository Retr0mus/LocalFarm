package com.github.countrybros.application.product;

import com.github.countrybros.application.acceptancesubmission.ISubmissionService;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.infrastructure.product.IItemRepository;
import com.github.countrybros.model.acceptancesubmission.Submission;
import com.github.countrybros.model.acceptancesubmission.AddProductSubmission;
import com.github.countrybros.model.acceptancesubmission.EditProductSubmission;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.ItemStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Concrete class of ItemDetails manager.
 */
@Service
public class ItemService implements IItemService {

    private final IItemRepository itemDetailsRepository;
    private final ICompanyService companyService;
    private final ISubmissionService acceptanceSubmissionService;

    public ItemService(IItemRepository repository, ICompanyService companyService,
                       ISubmissionService acceptanceSubmissionService) {

        this.itemDetailsRepository = repository;
        this.companyService = companyService;
        this.acceptanceSubmissionService = acceptanceSubmissionService;
    }


    @Override
    public void addItem(Item item) {

        itemDetailsRepository.save(item);
    }

    @Override
    public void deleteItemDetails(int itemDetailsId) {

        if (!itemDetailsRepository.existsById(itemDetailsId))
            throw new NotFoundInRepositoryException("Item details not found");

        itemDetailsRepository.deleteById(itemDetailsId);
    }

    @Override
    public void acceptChanges(int acceptanceSubmissionId) {

        Submission submission = acceptanceSubmissionService.getAcceptanceSubmission(acceptanceSubmissionId);

        if (submission instanceof AddProductSubmission sub)
            acceptItemDetails(sub.getItemDetailsId());

        else if (submission instanceof EditProductSubmission sub)
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

        Item item = getItemDetails(itemDetailsId);

        //TODO: implement
        //if (itemDetails.getStatus() != ItemStatus.underReview)
            //throw new ImpossibleRequestException("Item details not under review");

        item.setStatus(ItemStatus.available);
        itemDetailsRepository.save(item);
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

        Item existingItem = getItemDetails(existingItemDetailsId);
        Item changedItem = getItemDetails(changedItemDetailsId);

        if (!existingItem.getClass().equals(changedItem.getClass()))
            throw new ImpossibleRequestException("ItemDetails type not compatible");

        if (existingItem.equals(changedItem))
            throw new RequestAlreadySatisfiedException("Invalid ItemDetails edit request: changes already applied");

        if (!changedItem.getStatus().equals(ItemStatus.underReview))
            throw new ImpossibleRequestException("Changes can't be applied if they're not under review");

        if (!existingItem.getStatus().equals(ItemStatus.available)
                && !existingItem.getStatus().equals(ItemStatus.outOfStock))
            throw new ImpossibleRequestException("The ItemDetails to update has incompatible status");

        BeanUtils.copyProperties(changedItem, existingItem);
        existingItem.setStatus(ItemStatus.available);
        existingItem.setVisibleByPublic(true);

        //do not reverse this two lines
        deleteItemDetails(changedItemDetailsId);
        itemDetailsRepository.save(existingItem);
    }

    @Override
    public void setStatus(ItemStatus newStatus, int itemDetailsId) {

        Item item = getItemDetails(itemDetailsId);

        if (newStatus.equals(ItemStatus.available)) {
            if (item.getStatus().equals(ItemStatus.awaitingReview))
                throw new ImpossibleRequestException("Cannot change the status of an ItemDetails that is awaiting review");

            item.setVisibleByPublic(true);
        }

        if (newStatus.equals(ItemStatus.outOfStock) &&
                !item.getStatus().equals(ItemStatus.available))
            throw new ImpossibleRequestException("Cannot update to out of stock if not available");

        if (newStatus.equals(ItemStatus.underReview)
                && !item.getStatus().equals(ItemStatus.awaitingReview))
            throw new ImpossibleRequestException("Cannot update to under review if not awaiting review");

        if (newStatus.equals(ItemStatus.awaitingReview))
            throw new ImpossibleRequestException("Cannot update to awaiting review");

        item.setStatus(newStatus);
        itemDetailsRepository.save(item);
    }

    @Override
    public Item getItemDetails(int itemDetailsId) {

        Item item = itemDetailsRepository.findById(itemDetailsId).orElse(null);

        if (item == null)
            throw new NotFoundInRepositoryException("Item details not found");

        return item;
    }

    @Override
    public List<Item> getCompanyItemDetails(int companyId) {

        return itemDetailsRepository.findAllByProducer_Id(companyId);
    }
}
