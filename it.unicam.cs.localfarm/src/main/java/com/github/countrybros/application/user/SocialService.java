package com.github.countrybros.application.user;

import com.github.countrybros.application.event.IEventService;
import com.github.countrybros.application.product.IItemService;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.product.Item;
import org.springframework.stereotype.Service;

/**
 * Controller for managing the publication of contents in social media.
 */
@Service
public class SocialService implements ISocialService{

    private final IEventService eventService;
    private final IItemService itemService;

    public SocialService(IEventService eventService, IItemService itemDetailsService) {

        this.eventService = eventService;
        this.itemService = itemDetailsService;
    }

    @Override
    public void publishEvent(ISocialPublisher publisher, int eventId) {

        Event event = eventService.getEvent(eventId);
        publisher.publish(event.getPost());
    }

    @Override
    public void publishItemDetails(ISocialPublisher publisher, int ItemDetailsId) {

        Item item = itemService.getItem(ItemDetailsId);
        publisher.publish(item.getPost());
    }

}

