package ua.training.library.dao.factory;

import ua.training.library.dao.*;
import ua.training.library.dao.connection.AbstractConnection;

/**
 * <p> Interface for DAO Factory.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public interface DAOFactory {

    /**
     * Method returns DAO for Users operations.
     *
     * @param connection connection to database
     * @return UserDao
     */
    UserDAO getUserDAO(AbstractConnection connection);

    /**
     * Method returns DAO for Categories operations.
     *
     * @param connection connection to database
     * @return CategoryDao
     */
    CategoryDAO getCategoryDAO(AbstractConnection connection);

    /**
     * Method returns DAO for Catalogs operations.
     *
     * @param connection connection to database
     * @return CatalogDao
     */
    CatalogDAO getCatalogDAO(AbstractConnection connection);

    /**
     * Method returns DAO for Books operations.
     *
     * @param connection connection to database
     * @return BookDao
     */
    BookDAO getBookDAO(AbstractConnection connection);

    /**
     * Method returns DAO for Orders operations.
     *
     * @param connection connection to database
     * @return OrderDao
     */
    OrderDAO getOrderDAO(AbstractConnection connection);

    /**
     * Method returns DAO for Library Responses operations.
     *
     * @param connection connection to database
     * @return LibraryResponseDao
     */
    LibraryResponseDAO getLibraryResponseDAO(AbstractConnection connection);

}
