package com.github.countrybros.infrastructure.local;

import com.github.countrybros.infrastructure.ITransformedProductDetailsRepository;
import com.github.countrybros.model.product.TransformedProductDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Local repository of @TransformedProductDetails
 */
public class LocalTransformedProductDetailsRepository implements ITransformedProductDetailsRepository {

    private HashMap<Integer, TransformedProductDetails> repository;

    @Override
    public TransformedProductDetails get(int id) {

        return repository.get(id);
    }

    @Override
    public void delete(int id) {

        repository.remove(id);
    }

    @Override
    public void save(TransformedProductDetails productDetails) {

        repository.put(productDetails.getId(), productDetails);
    }

    @Override
    public boolean exists(int id) {

        return repository.containsKey(id);
    }

    @Override
    public List<TransformedProductDetails> getAll() {
        return new ArrayList<TransformedProductDetails>(repository.values());
    }
}
