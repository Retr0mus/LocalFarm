package com.github.countrybros.infrastructure.product;

import com.github.countrybros.model.product.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends CrudRepository<Item, Integer> {

        List<Item> findAllByProducer_Id(int producerId);
}
