package com.github.countrybros.model.social;

/**
 * Represents a generic social post.
 */
public class SocialPost {

    String title;
    String description;
    String link;

    public SocialPost(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }
}
