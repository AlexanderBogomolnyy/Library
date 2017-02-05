package ua.training.library.dao;

import ua.training.library.model.entity.Book;

import java.util.List;

/**
 * <p> The Book DAO interface.
 * This interface provides methods for working with book example data in data base.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public interface BookDAO extends GenericDAO<Book> {

    /**
     * This method looks for book examples included in catalog with provided catalog id
     * which located in library archive.
     *
     * @param catalogId - id of catalog element
     * @return - list of book examples
     */
    List<Book> getLocatedInLibraryByCatalogId(int catalogId);
}
