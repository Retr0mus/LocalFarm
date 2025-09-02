package com.github.countrybros.application.factories;

import com.github.countrybros.application.builders.BundleBuilder;
import com.github.countrybros.application.builders.IItemBuilder;
import com.github.countrybros.application.builders.SimpleProductBuilder;
import com.github.countrybros.application.builders.TransformedProductBuilder;
import com.github.countrybros.application.errors.SevereCodingErrorException;
import com.github.countrybros.model.item.ItemType;

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
