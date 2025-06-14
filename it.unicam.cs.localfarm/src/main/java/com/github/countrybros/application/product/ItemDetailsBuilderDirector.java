package com.github.countrybros.application.product;

import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.model.product.*;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.product.requests.*;

import java.util.ArrayList;

/**
 * Director class to manage the building of different types of ItemDetails
 */
public class ItemDetailsBuilderDirector {

    private final IItemDetailsService itemDetailsService;
    private final ICompanyService companyService;
    private final ItemDetailsBuilderFactory builderFactory;

    private IItemDetailsBuilder detailsBuilder;

    public ItemDetailsBuilderDirector(ICompanyService companyService, ItemDetailsBuilderFactory builderFactory,
                                      IItemDetailsService itemDetailsService) {
        this.companyService = companyService;
        this.builderFactory = builderFactory;
        this.itemDetailsService = itemDetailsService;
    }

    /**
     * Creates an ItemDetails with the respective request DTO.
     *
     * @param request The DTO for the request.
     *
     * @return The ItemDetails desired.
     */
    public ItemDetails createItemDetails (AddItemDetailsRequest request) {

        ItemType itemType = ItemType.valueOf(request.type);
        detailsBuilder = builderFactory.getBuilder(itemType);
        buildBaseItemDetails(request);

        switch (itemType) {
            case bundle -> buildBundleDetails((AddBundleDetailsRequest) request, (BundleDetailsBuilder) detailsBuilder);

            case simpleProduct -> buildSimpleProductDetails((AddSimpleProductDetailsRequest) request, (SimpleProductDetailsBuilder) detailsBuilder);

            case transformedProduct -> buildTransformedProductDetails((AddTransformedProductDetailsRequest) request, (TransformedProductDetailsBuilder) detailsBuilder);

            default -> throw new IllegalArgumentException("Unsupported item type");
        }

        return detailsBuilder.build();
    }

    private void buildBaseItemDetails (AddItemDetailsRequest request) {

        Company producer = companyService.getCompany(request.producerId);
        detailsBuilder.setName(request.name);
        detailsBuilder.setDescription(request.description);
        detailsBuilder.setProducer(producer);
    }

    private void buildBundleDetails (AddBundleDetailsRequest request, BundleDetailsBuilder builder) {

        ArrayList<ItemDetailsQuantity> itemsQuantity = new ArrayList<>();

        for (IdQuantityWrapper wrapper : request.items) {

            ItemDetails itemDetails = itemDetailsService.getItemDetails(wrapper.id);
            ItemDetailsQuantity itemQuantity = new ItemDetailsQuantity(itemDetails, wrapper.quantity);
            itemsQuantity.add(itemQuantity);
        }

        builder.setItemsQta(itemsQuantity);
    }

    private void buildSimpleProductDetails (AddSimpleProductDetailsRequest request, SimpleProductDetailsBuilder builder) {

        builder.setCertifications(request.certifications);
    }

    private void buildTransformedProductDetails (AddTransformedProductDetailsRequest request, TransformedProductDetailsBuilder builder) {

        buildSimpleProductDetails(request, builder);
        builder.setTransformationSteps(request.steps);
    }
}
