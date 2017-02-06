package ua.training.library.dao.factory;

import ua.training.library.messages.LoggingMessages;
import ua.training.library.dao.*;
import ua.training.library.dao.connection.AbstractConnection;
import ua.training.library.dao.mysqldao.*;
import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * <p> Factory for DAO's.
 * Singleton.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class MySqlDAOFactory implements DAOFactory {

    /**
     * Logger for DAOFactoryImpl class
     */
    private static final Logger logger = Logger.getLogger(MySqlDAOFactory.class);

    private MySqlDAOFactory(){ }

    private static class Holder {
        private static final MySqlDAOFactory INSTANCE = new MySqlDAOFactory();
    }

    /**
     * Getter for DAOFactory instance.
     *
     * @return instance of DAOFactory.
     */
    public static DAOFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public UserDAO getUserDAO(AbstractConnection connection) {
        return new MySqlUserDAO(checkedConnection(connection));
    }

    @Override
    public CategoryDAO getCategoryDAO(AbstractConnection connection) {
        return new MySqlCategoryDAO(checkedConnection(connection));
    }

    @Override
    public CatalogDAO getCatalogDAO(AbstractConnection connection) {
        return new MySqlCatalogDAO(checkedConnection(connection));
    }

    @Override
    public BookDAO getBookDAO(AbstractConnection connection) {
        return new MySqlBookDAO(checkedConnection(connection));
    }

    @Override
    public OrderDAO getOrderDAO(AbstractConnection connection) {
        return new MySqlOrderDAO(checkedConnection(connection));
    }

    @Override
    public LibraryResponseDAO getLibraryResponseDAO(AbstractConnection connection) {
        return new MySqlLibraryResponseDAO(checkedConnection(connection));
    }

    private Connection checkedConnection(AbstractConnection connection) {
        if(connection != null && connection.getConnection() instanceof Connection) {
            return (Connection)connection.getConnection();
        } else {
            logger.error(LoggingMessages.ERROR_WITH_DAO_CREATING);
            throw new RuntimeException();
        }
    }
}
