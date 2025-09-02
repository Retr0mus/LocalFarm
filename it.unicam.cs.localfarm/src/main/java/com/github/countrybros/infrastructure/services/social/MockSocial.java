package com.github.countrybros.infrastructure.services.social;

import com.github.countrybros.application.abstractions.ISocialPublisher;
import com.github.countrybros.model.social.SocialPost;

public class MockSocial implements ISocialPublisher {
    /**
     * Generates a post on the social that represents.
     *
     * @param post The post to publish.
     */
    @Override
    public void publish(SocialPost post) {

    }
}
