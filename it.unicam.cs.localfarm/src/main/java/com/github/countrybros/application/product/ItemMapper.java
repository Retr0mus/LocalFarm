package com.github.countrybros.application.product;

import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.model.product.*;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.product.requests.*;

import java.util.ArrayList;

/**
 * Director class to manage the building of different types of ItemDetails
 */
public class ItemMapper {

    private final ICompanyService companyService;
    private final ICertificationService certificationService;

    public ItemMapper(ICompanyService companyService,
                      ICertificationService certificationService) {
        this.companyService = companyService;
        this.certificationService = certificationService;
    }

    /**
     * Creates an ItemDetails with the respective request DTO.
     *
     * @param request The DTO for the request.
     *
     * @return The ItemDetails desired.
     */
    public Item toDomain(AddItemRequest request) {

        ItemType itemType = ItemType.valueOf(request.type);
        Item item;

        switch (itemType) {

            case bundle -> {
                item = new Bundle();
                buildBundleDetails((AddBundleRequest) request, (Bundle) item);
            }

            case simpleProduct ->  {
                item = new SimpleProduct();
                buildSimpleProductDetails((AddSimpleProductRequest) request, (SimpleProduct) item);
            }

            case transformedProduct -> {
                item = new TransformedProduct();
                buildTransformedProductDetails((AddTransformedProductRequest) request, (TransformedProduct) item);
            }

            default -> throw new IllegalArgumentException("Unsupported item type");
        }

        return item;
    }

    private void buildBaseItemDetails (AddItemRequest request, Item item) {

        Company producer = companyService.getCompany(request.producerId);

        item.setName(request.name);
        item.setDescription(request.description);
        item.setProducer(producer);
    }

    private void buildBundleDetails (AddBundleRequest request, Bundle bundle) {

        buildBaseItemDetails(request, bundle);



        bundle.setItems(request.items);
    }

    private void buildSimpleProductDetails (AddSimpleProductRequest request, SimpleProduct product) {

        buildBaseItemDetails(request, product);

        ArrayList<Certification> certifications = new ArrayList<>();
        for (int id: request.certificationIds)
            certifications.add(certificationService.getCertificationById(id));

        product.setCertifications(certifications);
    }

    private void buildTransformedProductDetails (AddTransformedProductRequest request, TransformedProduct product) {

        buildSimpleProductDetails(request, product);

        product.setSteps(request.steps);
    }


}
