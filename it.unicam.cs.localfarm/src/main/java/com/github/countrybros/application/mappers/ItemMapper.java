package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.item.BundleDto;
import com.github.countrybros.application.models.dtos.item.ItemDto;
import com.github.countrybros.application.models.dtos.item.SimpleProductDto;
import com.github.countrybros.application.models.dtos.item.TransformedProductDto;
import com.github.countrybros.application.models.requests.item.AddBundleRequest;
import com.github.countrybros.application.models.requests.item.AddItemRequest;
import com.github.countrybros.application.models.requests.item.AddSimpleProductRequest;
import com.github.countrybros.application.models.requests.item.AddTransformedProductRequest;
import com.github.countrybros.application.services.item.ICertificationService;
import com.github.countrybros.application.services.item.IItemService;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.model.item.*;
import com.github.countrybros.model.company.Company;

import java.util.ArrayList;

/**
 * Director class to manage the building of different types of ItemDetails and their DTOs
 */
public class ItemMapper {

    private final ICompanyService companyService;
    private final ICertificationService certificationService;
    private final IItemService itemService;

    public ItemMapper(ICompanyService companyService,
                      ICertificationService certificationService,
                      IItemService itemService) {
        this.companyService = companyService;
        this.certificationService = certificationService;
        this.itemService = itemService;
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

    public static ItemDto toDto(Item item) {

        ItemDto dto = null;

        if (item instanceof SimpleProduct) {
            if (item instanceof TransformedProduct) {
                dto = toTransformedProductDto((TransformedProduct) item);
            }
            else{
                dto = toSimpleProductDto((SimpleProduct) item);
            }
        } else if (item instanceof Bundle) {
                dto = toBundleDto((Bundle) item);
        } else {
            throw new IllegalArgumentException("Unsupported item type");
        }

        return dto;
    }

    private static SimpleProductDto toSimpleProductDto(SimpleProduct simpleProduct) {
        SimpleProductDto dto = new SimpleProductDto();
        dto.setId(simpleProduct.getId());
        dto.setName(simpleProduct.getName());
        dto.setDescription(simpleProduct.getDescription());
        dto.setStatus(simpleProduct.getStatus());
        dto.setProducerId(simpleProduct.getProducer().getId());
        dto.setCertifications(simpleProduct.getCertifications());
        return dto;
    }

    private static TransformedProductDto toTransformedProductDto(TransformedProduct transformedProduct) {
        TransformedProductDto dto = new TransformedProductDto();
        dto.setId(transformedProduct.getId());
        dto.setName(transformedProduct.getName());
        dto.setDescription(transformedProduct.getDescription());
        dto.setStatus(transformedProduct.getStatus());
        dto.setProducerId(transformedProduct.getProducer().getId());
        dto.setCertifications(transformedProduct.getCertifications());
        dto.setTransformationStepList(transformedProduct.getSteps());
        return dto;
    }

    private static BundleDto toBundleDto(Bundle bundle) {
        BundleDto dto = new BundleDto();
        dto.setId(bundle.getId());
        dto.setName(bundle.getName());
        dto.setDescription(bundle.getDescription());
        dto.setStatus(bundle.getStatus());
        dto.setProducerId(bundle.getProducer().getId());
        dto.setItems(bundle.getItems());
        return dto;
    }

    private void buildBaseItemDetails (AddItemRequest request, Item item) {

        Company producer = companyService.getCompany(request.producerId);

        item.setName(request.name);
        item.setDescription(request.description);
        item.setProducer(producer);
    }

    private void buildBundleDetails (AddBundleRequest request, Bundle bundle) {

        buildBaseItemDetails(request, bundle);

        for (int i : request.items.keySet())
            itemService.getItem(i);

        bundle.setItems(request.items);
    }

    private void buildSimpleProductDetails (AddSimpleProductRequest request, SimpleProduct product) {

        buildBaseItemDetails(request, product);

        ArrayList<Certification> certifications = new ArrayList<>();
        for (int id: request.certificationIds)
            certifications.add(certificationService.getCertification(id));

        product.setCertifications(certifications);
    }

    private void buildTransformedProductDetails (AddTransformedProductRequest request, TransformedProduct product) {

        // TODO: Uncomment when the DTO will be added
        /*buildSimpleProductDetails(request, item);

        List<SimpleProduct> items = new ArrayList<>();
        List<TransformationStep> steps = new ArrayList<>();

        for (TransformationStepDTO s : request.steps) {
            TransformationStep step = new TransformationStep();
            step.setDescription(s.description);
            step.setLocation(s.location);

            for (int i : s.ingredientsIds) {
                Item item = itemService.getItem(i);
                if (!(item instanceof SimpleProduct))
                    throw new IllegalArgumentException("Unsupported item type");
                items.add((SimpleProduct) item);
            }
            step.setIngredients(items);

            steps.add(step);
        }

        item.setSteps(steps);*/
    }
}
