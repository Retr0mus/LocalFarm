package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.infrastructure.product.IItemDetailsRepository;
import com.github.countrybros.model.product.ItemDetails;
import com.github.countrybros.model.product.ItemDetailsStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete class of ItemDetails manager.
 */
@Service
public class ItemDetailsService implements IItemDetailsService {

    private final IItemDetailsRepository itemDetailsRepository;
    //TODO: remove if it's not necessary
    private final ICompanyService companyService;

    public ItemDetailsService(IItemDetailsRepository repository, ICompanyService companyService) {

        this.itemDetailsRepository = repository;
        this.companyService = companyService;
    }

    @Override
    public void addItemDetails(ItemDetails itemDetails) {

        itemDetailsRepository.save(itemDetails);
    }

    @Override
    public void deleteItemDetails(int itemDetailsId) {

        if (!itemDetailsRepository.existsById(itemDetailsId))
            throw new NotFoundInRepositoryException("Item details not found");

        itemDetailsRepository.deleteById(itemDetailsId);
    }

    @Override
    public void acceptChanges(int existingItemDetailsId, int changedItemDetailsId) {

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

        //TODO: if this causes any error in hibernate (shared references): change to deep copy
        BeanUtils.copyProperties(changedItemDetails, existingItemDetails);
        existingItemDetails.setStatus(ItemDetailsStatus.available);
        existingItemDetails.setVisibleByPublic(true);

        //the order of this two lines does matter because of the light copy
        deleteItemDetails(changedItemDetailsId);
        itemDetailsRepository.save(existingItemDetails);
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

        //TODO: create query when Company is not Transient anymore.
        return new ArrayList<>();
    }
}
