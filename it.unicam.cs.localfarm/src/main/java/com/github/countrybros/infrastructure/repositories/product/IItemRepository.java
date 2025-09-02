package com.github.countrybros.infrastructure.repositories.product;

import com.github.countrybros.model.item.Item;
import com.github.countrybros.model.item.ItemStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends CrudRepository<Item, Integer> {

    List<Item> findAllByProducer_Id(int producerId);

    List<Item> findAllByStatus(ItemStatus status);

    boolean existsByName(String name);
}
