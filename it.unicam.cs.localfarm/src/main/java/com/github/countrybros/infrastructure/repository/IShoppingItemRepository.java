package com.github.countrybros.infrastructure.repository;

import com.github.countrybros.model.user.ShoppingItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingItemRepository extends CrudRepository<ShoppingItem, Integer> {
    /*
        Default function of CrudRepository
        save(…) – save an Iterable of entities. Here, we can pass multiple objects to save them in a batch
        findOne(…) – get a single entity based on passed primary key value
        findAll() – get an Iterable of all available entities in the database
        count() – return the count of total entities in a table
        delete(…) – delete an entity based on the passed object
        exists(…) – verify if an entity exists based on the passed primary key value
     */
}
