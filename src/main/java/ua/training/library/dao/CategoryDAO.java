package ua.training.library.dao;

import ua.training.library.model.entity.Category;

import java.util.Optional;

/**
 * <p> The Category DAO interface.
 * This interface provides methods for working with category data in data base.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public interface CategoryDAO extends GenericDAO<Category> {

    /**
     * This method looks for category with specific name
     *
     * @param name - category name
     * @return - category
     */
    Optional<Category> getByName(String name);
}
