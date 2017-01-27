package ua.training.library.service.impl;

import ua.training.library.config.ActionMessages;
import ua.training.library.dao.UserDAO;
import ua.training.library.dao.connection.AbstractConnection;
import ua.training.library.dao.connection.ConnectionFactory;
import ua.training.library.dao.connection.ConnectionFactoryImpl;
import ua.training.library.dao.factory.DAOFactory;
import ua.training.library.dao.factory.DAOFactoryImpl;
import ua.training.library.model.entity.User;
import org.apache.log4j.Logger;
import ua.training.library.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    /**
     * Logger for logging errors
     */
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    private UserServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl(ConnectionFactoryImpl.getInstance(),
                DAOFactoryImpl.getInstance());
    }

    public static UserServiceImpl getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public Optional<User> login(String login, String password) {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.getByLoginAndPassword(login, password);
        }
    }

    @Override
    public Optional<User> getById(int id) {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.getById(id);
        }
    }

    @Override
    public Optional<User> getByLoginAndEmail(String login, String email) {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.getByLoginAndEmail(login, email);
        }
    }

    @Override
    public List<User> getAll() {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.getAll();
        }
    }

    @Override
    public void create(User user) {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            connection.beginTransaction();
            if (userDAO.getByLoginAndPassword(user.getLogin(), user.getEmail()).isPresent())
                throw new RuntimeException(ActionMessages.ERROR_WITH_CREATING_USER);
            else
                userDAO.create(user);
            connection.commit();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
