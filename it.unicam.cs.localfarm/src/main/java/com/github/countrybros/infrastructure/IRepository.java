package com.github.countrybros.infrastructure;


import java.util.List;

/**
 * Generic repository that woks with Integer IDs
 */
@Deprecated
public interface IRepository<T> {

    /**
     * Finds the specified element, if exists.
     *
     * @param id the ID
     *
     * @return The element
     */
    public T get(int id);

    /**
     * Deletes the specified element, if exists.
     *
     * @param id ID
     */
    public void delete(int id);

    /**
     * Edits an exixting element, or creates if not present.
     *
     * @param obj the element to save
     */
    public void save(T obj);

    boolean exists(int id);

    /**
     * Returns all the elements in the repository.
     *
     * @return A list with every element.
     */
    List<T> getAll();
}

