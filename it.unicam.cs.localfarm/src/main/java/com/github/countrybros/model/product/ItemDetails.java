package com.github.countrybros.model.product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.countrybros.model.user.Company;
import jakarta.persistence.*;

/**
 * Represents the details of a generic @Item that can be sold in the marketplace.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,            // Specifies that the type information will be included as a logical name (simple string) identifying the concrete class.
        include = JsonTypeInfo.As.PROPERTY,   // Specifies that this type information will be included as a property inside the JSON object.
        property = "type"                      // Defines the name of the JSON property that will hold the type information (here, "type").
)
@JsonSubTypes({
        // Defines the possible subtypes and associates each subtype with a specific name value in the "type" property.
        @JsonSubTypes.Type(value = SimpleProductDetails.class, name = "simpleProductDetails"),
        @JsonSubTypes.Type(value = TransformedProductDetails.class, name = "transformedProductDetails"),
        @JsonSubTypes.Type(value = BundleDetails.class, name = "bundleDetails")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "submission_type")
public abstract class ItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;

    private String description;

    private ItemDetailsStatus status;

    private boolean visibleByPublic = false;

    @OneToOne
    private Company producer;

    public ItemDetails() {
        this.status = ItemDetailsStatus.awaitingReview;
        this.visibleByPublic = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public ItemDetailsStatus getStatus() {
        return status;
    }

    public void setStatus(ItemDetailsStatus status) {

        this.status = status;
    }

    public boolean getIsVisibleByPublic() {
        return visibleByPublic;
    }

    public void setVisibleByPublic(boolean visibleByPublic) {

        this.visibleByPublic = visibleByPublic;
    }

    public Company getProducer() {
        return producer;
    }

    public int getId() {
        return Id;
    }

    public void setProducer(Company producer) {
        this.producer = producer;
    }
}
