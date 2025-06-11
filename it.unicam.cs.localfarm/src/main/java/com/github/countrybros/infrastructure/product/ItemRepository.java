package com.github.countrybros.infrastructure.product;

import com.github.countrybros.model.product.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface for item data access and manipulation.
 */

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

}
