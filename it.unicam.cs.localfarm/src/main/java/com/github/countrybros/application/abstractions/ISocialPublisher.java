package com.github.countrybros.application.abstractions;

import com.github.countrybros.model.social.SocialPost;

/**
 * Represents a generic social media API.
 */
public interface ISocialPublisher {

    /**
     * Generates a post on the social that represents.
     *
     * @param post The post to publish.
     */
    void publish(SocialPost post);
}
