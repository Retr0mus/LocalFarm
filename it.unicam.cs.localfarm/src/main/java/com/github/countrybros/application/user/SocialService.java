package com.github.countrybros.application.user;

import com.github.countrybros.application.event.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Controller for managing the publication of contents in social media.
 */
@Service
public class SocialService implements ISocialService{

    //TODO
    @Override
    public void publishEvent(ISocialPublisher publisher, int eventId) {

    }

    @Override
    public void publishItemDetails(ISocialPublisher publisher, int ItemDetailsId) {

    }
}

