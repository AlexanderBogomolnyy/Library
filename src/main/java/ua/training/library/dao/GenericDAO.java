package ua.training.library.dao;

import java.util.List;
import java.util.Optional;

/**
 * <p> The Generic DAO interface.
 * This interface provides common methods for working with some kind of data,
 * which contained in data base.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public interface GenericDAO<E> {
    /**
     * This method return element by it's ID
     *
     * @param id - element id
     * @return - element
     */
    Optional<E> getById(int id);

    /**
     * This method return list of all elements described by this interface implementation
     *
     * @return list of elements
     */
    List<E> getAll();

    /**
     * This method creates element in data base
     *
     * @param e - elsment
     */
    void create(E e);

    /**
     * This method updates element in data base
     *
     * @param e - element
     */
    void update(E e);

    /**
     * This method delete the element with provided id from data base
     *
     * @param id - element id
     */
    void delete(int id);
}

