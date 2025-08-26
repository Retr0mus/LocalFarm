package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.SevereCodingErrorException;
import com.github.countrybros.model.product.ItemType;

/**
 * Creates all the items by their type
 */
public class ItemBuilderFactory {

    public IItemBuilder getBuilder(ItemType type) {

        switch (type) {

            case simpleProduct -> {
                return new SimpleProductBuilder();
            }
            case transformedProduct -> {
                return new TransformedProductBuilder();
            }
            case bundle -> {
                return new BundleBuilder();
            }
            default -> {
                throw new SevereCodingErrorException("Item type not managed");
            }
        }
    }
}
