package ua.training.library.service.impl;

import org.apache.log4j.Logger;
import ua.training.library.config.ActionMessages;
import ua.training.library.dao.*;
import ua.training.library.dao.connection.AbstractConnection;
import ua.training.library.dao.connection.ConnectionFactory;
import ua.training.library.dao.connection.ConnectionFactoryImpl;
import ua.training.library.dao.factory.DAOFactory;
import ua.training.library.dao.factory.DAOFactoryImpl;
import ua.training.library.model.entity.Book;
import ua.training.library.model.entity.LibraryResponse;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.entity.states.BookStatus;
import ua.training.library.model.entity.states.OrderType;
import ua.training.library.model.entity.states.Role;
import ua.training.library.service.LibraryResponseService;
import ua.training.library.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//TODO rename BasicLibraryResponseService

public class LibraryResponseServiceImpl implements LibraryResponseService {

    private static final Logger logger = Logger.getLogger(LibraryResponseServiceImpl.class);

    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    LibraryResponseServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        private static final LibraryResponseService INSTANCE = new LibraryResponseServiceImpl(ConnectionFactoryImpl.getInstance(),
                DAOFactoryImpl.getInstance()) {
        };
    }

    public static LibraryResponseService getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<LibraryResponse> getAvailableForUser(int userId, Role role) {
        try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            if (role == Role.LIBRARIAN) {
                LibraryResponseDAO responseDAO = daoFactory.getLibraryResponseDAO(connection);
                return responseDAO.getByLibrarianId(userId);
            } else {
                logger.warn(ActionMessages.NO_PERMISSION);
                throw new ServiceException(ActionMessages.NO_PERMISSION);
            }
        }
    }

    @Override
    public void createNewLibraryResponse(LibraryResponse response, Role role) {
        if (role == Role.LIBRARIAN) {
            try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
                connection.beginTransaction();
                LibraryResponseDAO responseDAO = daoFactory.getLibraryResponseDAO(connection);
                if (verificationOfConditions(response, connection)) {
                    responseDAO.createNewLibraryResponseWithUpdateOrder(response);
                    connection.commit();
                } else {
                    connection.rollback();
                    throw new ServiceException("This book already issued or order already processed.");
                }
            }
        } else {
            throw new ServiceException(ActionMessages.NO_PERMISSION);
        }
    }

    @Override
    public void completeLibraryResponseByCurrentDate(int libraryResponseID, Role role) {
        if (role == Role.LIBRARIAN) {
            try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
                LocalDate dateOfReturn = LocalDate.now();
                LibraryResponseDAO libraryResponseDAO = daoFactory.getLibraryResponseDAO(connection);
                int numberOfUpdated = libraryResponseDAO.updateAndCompleteLibraryResponse(libraryResponseID, dateOfReturn);
                if (numberOfUpdated == 0)
                    throw new ServiceException("This order and library response already completed.");
            }
        } else {
            throw new ServiceException(ActionMessages.NO_PERMISSION);
        }
    }

    private boolean verificationOfConditions(LibraryResponse response, AbstractConnection connection) {
        OrderDAO orderDAO = daoFactory.getOrderDAO(connection);
        BookDAO bookDAO = daoFactory.getBookDAO(connection);
        OrderType orderType = orderDAO.getById(response.getOrderId()).orElseGet(Order::new).getType();
        Book selectedBook = bookDAO.getById(response.getBookId()).orElseGet(Book::new);
        return orderType == OrderType.NEW
                && selectedBook.getStatus() == BookStatus.ACTIVE
                && selectedBook.getLocation() == BookLocation.LIBRARY;
    }

    @Override
    public Optional<LibraryResponse> getById(int id) {
        try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            LibraryResponseDAO responseDAO = daoFactory.getLibraryResponseDAO(connection);
            return responseDAO.getById(id);
        }
    }

    @Override
    public List<LibraryResponse> getAll() {
        try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            LibraryResponseDAO responseDAO = daoFactory.getLibraryResponseDAO(connection);
            return responseDAO.getAll();
        }
    }

    @Override
    public void create(LibraryResponse response) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(LibraryResponse response) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}
