package ua.training.library.dao.factory;

import ua.training.library.config.LoggingMessages;
import ua.training.library.dao.*;
import ua.training.library.dao.connection.AbstractConnection;
import ua.training.library.dao.impl.*;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class DAOFactoryImpl implements DAOFactory {

    private static final Logger logger = Logger.getLogger(DAOFactoryImpl.class);

    private DAOFactoryImpl(){ }

    private static class Holder {
        private static final DAOFactoryImpl INSTANCE = new DAOFactoryImpl();
    }

    public static DAOFactoryImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public UserDAO getUserDAO(AbstractConnection connection) {
        return new UserDAOImpl(checkedConnection(connection));
    }

    @Override
    public CategoryDAO getCategoryDAO(AbstractConnection connection) {
        return new CategoryDAOImpl(checkedConnection(connection));
    }

    @Override
    public CatalogDAO getCatalogDAO(AbstractConnection connection) {
        return new CatalogDAOImpl(checkedConnection(connection));
    }

    @Override
    public BookDAO getBookDAO(AbstractConnection connection) {
        return new BookDAOImpl(checkedConnection(connection));
    }

    @Override
    public OrderDAO getOrderDAO(AbstractConnection connection) {
        return new OrderDAOImpl(checkedConnection(connection));
    }

    @Override
    public LibraryResponseDAO getLibraryResponseDAO(AbstractConnection connection) {
        return null;
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
