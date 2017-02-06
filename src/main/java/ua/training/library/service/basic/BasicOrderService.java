package ua.training.library.service.basic;

import org.apache.log4j.Logger;
import ua.training.library.messages.ServiceMessages;
import ua.training.library.dao.BookDAO;
import ua.training.library.dao.CatalogDAO;
import ua.training.library.dao.OrderDAO;
import ua.training.library.dao.UserDAO;
import ua.training.library.dao.connection.AbstractConnection;
import ua.training.library.dao.connection.ConnectionFactory;
import ua.training.library.dao.connection.ConnectionFactoryImpl;
import ua.training.library.dao.factory.DAOFactory;
import ua.training.library.dao.factory.MySqlDAOFactory;
import ua.training.library.model.entity.Book;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.states.ActivationStatus;
import ua.training.library.model.entity.states.BookStatus;
import ua.training.library.model.entity.states.OrderType;
import ua.training.library.model.entity.states.Role;
import ua.training.library.service.OrderService;
import ua.training.library.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BasicOrderService implements OrderService {

    private static final Logger logger = Logger.getLogger(BasicCatalogService.class);

    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    BasicOrderService(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        private static final OrderService INSTANCE = new BasicOrderService(ConnectionFactoryImpl.getInstance(),
                MySqlDAOFactory.getInstance());
    }

    public static OrderService getInstance(){
        return Holder.INSTANCE;
    }


    @Override
    public Optional<Order> getById(int id) {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            OrderDAO orderDAO = daoFactory.getOrderDAO(connection);
            return orderDAO.getById(id);
        }
    }

    @Override
    public List<Book> getBooksForOrder(Order order) {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            BookDAO bookDAO = daoFactory.getBookDAO(connection);
            return bookDAO.getLocatedInLibraryByCatalogId(order.getCatalogId());
        }
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
                    logger.warn(ServiceMessages.NO_PERMISSION);
                    throw new ServiceException(ServiceMessages.NO_PERMISSION);
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
    public void createNewOrder(Order order, Role role) {
        if (role == Role.CLIENT) {
            try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
                connection.beginTransaction();
                if (isCreatingPossible(order, connection)) {
                    daoFactory.getOrderDAO(connection).createNewOrder(order);
                    connection.commit();
                } else {
                    connection.rollback();
                    logger.error(ServiceMessages.ERROR_WITH_CREATING_NEW_ORDER);
                    throw new ServiceException(ServiceMessages.ERROR_WITH_CREATING_NEW_ORDER);
                }
            }
        } else {
            logger.warn(ServiceMessages.NO_PERMISSION);
            throw new ServiceException(ServiceMessages.NO_PERMISSION);
        }
    }

    @Override
    public void updateAndComplete(int orderId, LocalDate dateOfReturn) {
        try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            connection.beginTransaction();
            OrderDAO orderDAO = daoFactory.getOrderDAO(connection);
            if (isDeletionPossible(orderId, orderDAO)) {
                orderDAO.updateAndComplete(orderId, dateOfReturn);
                connection.commit();
            } else {
                connection.rollback();
                logger.error(ServiceMessages.ERROR_WITH_UPDATING_AND_COMPLETING_IN_ORDER);
                throw new ServiceException(ServiceMessages.ERROR_WITH_UPDATING_AND_COMPLETING_IN_ORDER);
            }
        }
    }

    private boolean isDeletionPossible(int orderId, OrderDAO orderDAO) {
        return orderDAO.getById(orderId)
                .filter(c -> c.getType() != OrderType.COMPLETED)
                .isPresent();
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
