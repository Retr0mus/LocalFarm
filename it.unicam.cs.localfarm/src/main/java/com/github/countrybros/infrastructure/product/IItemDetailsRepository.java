package com.github.countrybros.infrastructure.product;

import com.github.countrybros.model.product.ItemDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemDetailsRepository extends CrudRepository<ItemDetails, Integer> {
}
