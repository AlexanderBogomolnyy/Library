package ua.training.library.service.impl;

import org.apache.log4j.Logger;
import ua.training.library.config.ActionMessages;
import ua.training.library.dao.CatalogDAO;
import ua.training.library.dao.UserDAO;
import ua.training.library.dao.connection.AbstractConnection;
import ua.training.library.dao.connection.ConnectionFactory;
import ua.training.library.dao.connection.ConnectionFactoryImpl;
import ua.training.library.dao.factory.DAOFactory;
import ua.training.library.dao.factory.DAOFactoryImpl;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.states.ActivationStatus;
import ua.training.library.model.entity.states.BookStatus;
import ua.training.library.model.entity.states.Role;
import ua.training.library.service.OrderService;
import ua.training.library.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = Logger.getLogger(CatalogServiceImpl.class);

    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    private OrderServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        private static final OrderService INSTANCE = new OrderServiceImpl(ConnectionFactoryImpl.getInstance(),
                DAOFactoryImpl.getInstance());
    }

    public static OrderService getInstance(){
        return Holder.INSTANCE;
    }


    @Override
    public Optional<Order> getById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getAllAvailable(int userId, Role role) {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            List<Order> orderList;
            switch (role) {
                case CLIENT:
                    orderList = getAllForClient(userId, connection);
                    break;
                case LIBRARIAN:
                    orderList = getAllForLibrarian(connection);
                    break;
                default:
                    throw new ServiceException(ActionMessages.NO_PERMISSION);
            }
            return orderList;
        }
    }

    private List<Order> getAllForClient(int clientId, AbstractConnection connection) {
        return daoFactory.getOrderDAO(connection).getAllByUserId(clientId);
    }

    private List<Order> getAllForLibrarian(AbstractConnection connection) {
        return daoFactory.getOrderDAO(connection).getAll();
    }

    @Override
    public void create(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createNewOrder(Order order) {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            connection.beginTransaction();
            if (isCreatingPossible(order, connection)) {
                daoFactory.getOrderDAO(connection).createNewOrder(order);
                connection.commit();
            } else {
                connection.rollback();
                throw new RuntimeException(ActionMessages.ERROR_WITH_CREATING_NEW_ORDER);
            }
        }
    }

    private boolean isCreatingPossible(Order order, AbstractConnection connection) {
        return isUserActive(order, connection) && isCatalogActive(order, connection);
    }

    private boolean isCatalogActive(Order order, AbstractConnection connection) {
        CatalogDAO catalogDAO = daoFactory.getCatalogDAO(connection);
        return catalogDAO
                .getById(order.getCatalogId())
                .filter(c -> c.getStatus() == BookStatus.ACTIVE)
                .isPresent();
    }

    private boolean isUserActive(Order order, AbstractConnection connection) {
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        return userDAO
                .getById(order.getUser().getId())
                .filter(u -> u.getStatus() == ActivationStatus.ACTIVE)
                .isPresent();
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

}
