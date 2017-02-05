package ua.training.library.dao;

import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.states.BookStatus;

import java.util.List;

/**
 * <p> The Catalog DAO interface.
 * This interface provides methods for working with catalog element data in data base.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public interface CatalogDAO extends GenericDAO<Catalog> {

    /**
     * This method looks for catalog elements with specific status
     *
     * @param status - catalog element status
     * @return - list of catalog
     */
    List<Catalog> getByStatus(BookStatus status);

    /**
     * This method looks for catalog elements with specific category id
     *
     * @param categoryId - category id
     * @return - list of catalog
     */
    List<Catalog> getByCategoryId(int categoryId);
}
