package ua.training.library.service.basic;

import ua.training.library.messages.ServiceMessages;
import ua.training.library.dao.UserDAO;
import ua.training.library.dao.connection.AbstractConnection;
import ua.training.library.dao.connection.ConnectionFactory;
import ua.training.library.dao.connection.ConnectionFactoryImpl;
import ua.training.library.dao.factory.DAOFactory;
import ua.training.library.dao.factory.MySqlDAOFactory;
import ua.training.library.model.entity.User;
import org.apache.log4j.Logger;
import ua.training.library.model.entity.states.Role;
import ua.training.library.service.UserService;
import ua.training.library.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class BasicUserService implements UserService {

    /**
     * Logger for logging errors
     */
    private static final Logger logger = Logger.getLogger(BasicUserService.class);

    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    BasicUserService(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        private static final UserService INSTANCE = new BasicUserService(ConnectionFactoryImpl.getInstance(),
                MySqlDAOFactory.getInstance());
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<User> login(String login, String password) {
        try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.getByLoginAndPassword(login, password);
        }
    }

    @Override
    public Optional<User> getById(int id) {
        try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.getById(id);
        }
    }

    @Override
    public List<User> getAllClients(Role role) {
        try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            if (role == Role.LIBRARIAN) {
                return userDAO.getAllByRole(Role.CLIENT);
            } else {
                throw new ServiceException(ServiceMessages.NO_PERMISSION);
            }
        }
    }

    @Override
    public void create(User user) {
        try (AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            connection.beginTransaction();
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            if (userDAO.getByLoginAndEmail(user.getLogin(), user.getEmail()).isPresent()) {
                connection.rollback();
                throw new ServiceException(ServiceMessages.ERROR_WITH_CREATING_USER);
            } else {
                userDAO.create(user);
            }
            connection.commit();
        }
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}
