package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.infrastructure.product.IItemRepository;
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

    private final IItemRepository itemRepository;

    public ItemService(IItemRepository repository) {

        this.itemRepository = repository;
    }


    @Override
    public void addItem(Item item) {

        itemRepository.save(item);
    }

    @Override
    public void deleteItemDetails(int itemDetailsId) {

        if (!itemRepository.existsById(itemDetailsId))
            throw new NotFoundInRepositoryException("Item details not found");

        itemRepository.deleteById(itemDetailsId);
    }

    /**
     * Accepts a submission, making the necessary changes.
     *
     * @param acceptanceSubmissionId The submission ID
     * @throws ImpossibleRequestException if the type of submission is not valid.
     */
    @Override
    public void acceptChanges(int acceptanceSubmissionId) {

    }

    /*@Override
    public void acceptChanges(int acceptanceSubmissionId) {

        Submission submission = acceptanceSubmissionService.getSubmission(acceptanceSubmissionId);

        if (submission instanceof AddProductSubmission sub)
            acceptItemDetails(sub.getItemDetailsId());

        else if (submission instanceof EditProductSubmission sub)
            editItem(sub.getProductToEditId(), sub.getProductChangeId());

        else
            throw new ImpossibleRequestException("Unsupported submission type");

        acceptanceSubmissionService.onAcception(acceptanceSubmissionId);
    }*/

    /**
     * Accept an item that is under review.
     *
     * @param itemDetailsId The itemDetails ID.
     */
    private void acceptItemDetails(int itemDetailsId) {

        Item item = getItem(itemDetailsId);

        //TODO: implement
        //if (itemDetails.getStatus() != ItemDetailsStatus.underReview)
            //throw new ImpossibleRequestException("Item details not under review");

        item.setStatus(ItemStatus.available);
        itemRepository.save(item);
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

        Item existingItem = getItem(existingItemDetailsId);
        Item changedItem = getItem(changedItemDetailsId);

        if (!existingItem.getClass().equals(changedItem.getClass()))
            throw new ImpossibleRequestException("ItemDetails type not compatible");

        if (existingItem.equals(changedItem))
            throw new RequestAlreadySatisfiedException("Invalid ItemDetails edit request: changes already applied");

        if (!changedItem.getStatus().equals(ItemStatus.underReview))
            throw new ImpossibleRequestException("Changes can't be applied if they're not under review");

        BeanUtils.copyProperties(changedItem, existingItem);
        existingItem.setStatus(ItemStatus.available);

        //do not reverse this two lines
        deleteItemDetails(changedItemDetailsId);
        itemRepository.save(existingItem);
    }

    @Override
    public void setStatus(ItemStatus newStatus, int itemDetailsId) {

        Item item = getItem(itemDetailsId);

        if (newStatus.equals(ItemStatus.available)) {
            if (item.getStatus().equals(ItemStatus.awaitingReview))
                throw new ImpossibleRequestException("Cannot change the status of an ItemDetails that is awaiting review");
        }

        if (newStatus.equals(ItemStatus.underReview)
                && !item.getStatus().equals(ItemStatus.awaitingReview))
            throw new ImpossibleRequestException("Cannot update to under review if not awaiting review");

        if (newStatus.equals(ItemStatus.awaitingReview))
            throw new ImpossibleRequestException("Cannot update to awaiting review");

        item.setStatus(newStatus);
        itemRepository.save(item);
    }

    @Override
    public Item getItem(int itemId) {

        Item item = itemRepository.findById(itemId).orElse(null);

        if (item == null)
            throw new NotFoundInRepositoryException("Item details not found");

        return item;
    }

    @Override
    public List<Item> getAvailableItems() {
        return itemRepository.findAllByStatus(ItemStatus.available);
    }

    @Override
    public List<Item> getCompanyItemDetails(int companyId) {
        return itemRepository.findAllByProducer_Id(companyId);
    }
}
