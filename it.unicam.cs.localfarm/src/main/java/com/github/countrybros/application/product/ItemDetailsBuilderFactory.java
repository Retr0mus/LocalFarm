package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.SevereCodingErrorException;
import com.github.countrybros.model.product.ItemType;

/**
 * Creates all the items by their type
 */
@Deprecated
public class ItemDetailsBuilderFactory {

    ItemDetailsBuilder getBuilder(ItemType type) {

        switch (type) {

            case simpleProduct -> {
                return new SimpleProductDetailsBuilder();
            }
            case transformedProduct -> {
                return new TransformedProductDetailsBuilder();
            }
            case bundle -> {
                return new BundleDetailsBuilder();
            }
            default -> {
                throw new SevereCodingErrorException("Item type not managed");
            }
        }
    }
}
