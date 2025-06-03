package com.github.countrybros.infrastructure.product;

import com.github.countrybros.model.product.ItemDetails;

import java.util.Optional;

/**
 * Repository of @SimpleProductDetails
 */
public class SimpleProductDetailsRepository implements IItemDetailsRepository {

    @Override
    public <S extends ItemDetails> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ItemDetails> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ItemDetails> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<ItemDetails> findAll() {
        return null;
    }

    @Override
    public Iterable<ItemDetails> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(ItemDetails entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends ItemDetails> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
