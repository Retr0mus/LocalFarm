package com.github.countrybros.infrastructure.local;

import com.github.countrybros.infrastructure.ISimpleProductDetailsRepository;
import com.github.countrybros.model.product.SimpleProductDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Local repository of @SimpleProductdetails
 */
public class LocalSimpleProductDetailsRepository implements ISimpleProductDetailsRepository {

    private HashMap<Integer, SimpleProductDetails> repository;

    @Override
    public SimpleProductDetails get(int id) {

        return repository.get(id);
    }

    @Override
    public void delete(int id) {

        repository.remove(id);
    }

    @Override
    public void save(SimpleProductDetails productDetails) {

        repository.put(productDetails.getId(),  productDetails);
    }

    @Override
    public boolean exists(int id) {

        return repository.containsKey(id);
    }

    @Override
    public List<SimpleProductDetails> getAll() {
        return new ArrayList<SimpleProductDetails>(repository.values());
    }
}
