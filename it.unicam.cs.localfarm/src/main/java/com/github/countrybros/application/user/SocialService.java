package com.github.countrybros.application.user;

import com.github.countrybros.application.event.IEventService;
import com.github.countrybros.application.product.IItemDetailsService;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.product.ItemDetails;
import com.github.countrybros.model.user.IPostable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Controller for managing the publication of contents in social media.
 */
@Service
public class SocialService implements ISocialService{

    private final IEventService eventService;
    private final IItemDetailsService itemDetailsService;

    public SocialService(IEventService eventService, IItemDetailsService itemDetailsService) {

        this.eventService = eventService;
        this.itemDetailsService = itemDetailsService;
    }

    @Override
    public void publishEvent(ISocialPublisher publisher, int eventId) {

        Event event = eventService.getEvent(eventId);
        publisher.publish(event.getPost());
    }

    @Override
    public void publishItemDetails(ISocialPublisher publisher, int ItemDetailsId) {

        ItemDetails itemDetails = itemDetailsService.getItemDetails(ItemDetailsId);
        publisher.publish(itemDetails.getPost());
    }

}

