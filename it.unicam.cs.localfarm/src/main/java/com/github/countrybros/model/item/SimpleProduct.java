package com.github.countrybros.model.item;

import com.github.countrybros.model.social.SocialPost;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Details for a simple item.
 */
@Entity
@DiscriminatorValue("simpleProduct")
public class SimpleProduct extends Item {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Certification> certifications = new ArrayList<>();

    public List<Certification> getCertifications() { return certifications; }

    public void setCertifications(ArrayList<Certification> certifications) {

        this.certifications = certifications;
    }

    @Override
    @Transient
    public SocialPost getPost() {
        return new SocialPost(getName(), getDescription(), "link");
    }
}
