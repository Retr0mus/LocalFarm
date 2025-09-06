package com.github.countrybros.application.services.item;

import com.github.countrybros.application.services.submission.ISubmissionService;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.infrastructure.repositories.product.IItemRepository;
import com.github.countrybros.model.item.Item;
import com.github.countrybros.model.item.ItemStatus;
import org.springframework.stereotype.Service;

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

        if (itemRepository.existsByName(item.getName()))
            throw new ImpossibleRequestException("An item with that name already exists");
        itemRepository.save(item);
    }


    @Override
    public Item getItem(int itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new NotFoundInRepositoryException("Item not found"));
    }

    @Override
    public void setStatus(ItemStatus newStatus, int itemId) {

        Item item = getItem(itemId);

        if (newStatus.equals(ItemStatus.available)) {
            if (item.getStatus().equals(ItemStatus.awaitingReview))
                throw new ImpossibleRequestException("Cannot change the status of an ItemDetails that is awaiting review");
        }

        if (newStatus.equals(ItemStatus.awaitingReview))
            throw new ImpossibleRequestException("Cannot update to awaiting review");

        item.setStatus(newStatus);
        itemRepository.save(item);
    }

    @Override
    public List<Item> getAvailableItems() {
        return itemRepository.findAllByStatus(ItemStatus.available);
    }

    @Override
    public void deleteItem(int itemId) {

        itemRepository.deleteById(itemId);
    }
}