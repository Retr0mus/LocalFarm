package com.github.countrybros.application.services.social;

import com.github.countrybros.application.abstractions.ISocialPublisher;

/**
 * Permits to publish posts on socials.
 */
public interface ISocialService {

    /**
     * Uses a @IsocialPublisher to create a post on an @Event.
     *
     * @param publisher The publisher to use.
     * @param eventId The ID of Event.
     */
    void publishEvent(ISocialPublisher publisher, int eventId);

    /**
     * Uses a @IsocialPublisher to create a post on a @ItemDetails.
     *
     * @param publisher The publisher to use
     * @param ItemDetailsId ID of the item
     */
    void publishItemDetails(ISocialPublisher publisher, int ItemDetailsId);
}
