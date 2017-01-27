package ua.training.library.dao.factory;

import ua.training.library.dao.*;
import ua.training.library.dao.connection.AbstractConnection;

public interface DAOFactory {

    UserDAO getUserDAO(AbstractConnection connection);
    CategoryDAO getCategoryDAO(AbstractConnection connection);
    CatalogDAO getCatalogDAO(AbstractConnection connection);
    BookDAO getBookDAO(AbstractConnection connection);
    OrderDAO getOrderDAO(AbstractConnection connection);
    LibraryResponseDAO getLibraryResponseDAO(AbstractConnection connection);

}
