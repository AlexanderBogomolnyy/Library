package ua.training.library.dao.impl;

import ua.training.library.config.LoggingMessages;
import ua.training.library.dao.UserDAO;
import ua.training.library.dao.exception.DAOException;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.ActivationStatus;
import ua.training.library.model.entity.states.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p> The class which implements User DAO interface,
 * and overrides it's methods.
 * This class uses MySQL queries for sending requests to the database.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class UserDAOImpl implements UserDAO {

    /**
     * Logger for UserDAOImpl class
     */
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    /**
     * The names of table fields
     */
    private static final String USER_ROLE_NAME = "user_role.name";
    private static final String ID = "user.id";
    private static final String LOGIN = "user.login";
    private static final String FIRST_NAME = "user.f_name";
    private static final String LAST_NAME = "user.l_name";
    private static final String EMAIL = "user.email";
    private static final String PASSWORD = "user.password";
    private static final String STATUS = "user.status";

    /**
     * The queries to the data base
     */
    private static final String GET_ALL = "SELECT * FROM `user` " +
            "LEFT JOIN `user_role` ON `user`.`role_id` = `user_role`.`id` ";
    private static final String GET_BY_LOGIN_AND_PASSWORD = GET_ALL + "WHERE `user`.`login` = ? AND `user`.`password` = ?";
    private static final String GET_BY_ID = GET_ALL + "WHERE `user`.`id` = ?";
    private static final String GET_ALL_BY_ROLE = GET_ALL + "WHERE `user_role`.`name` = ?";
    private static final String CREATE_USER = "INSERT INTO `user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`) " +
            "  VALUE(?, ?, ?, ?, ?, ?, ?)";

    /**
     * This field contains connection to the data base
     */
    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> getByLoginAndPassword(String login, String password) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_LOGIN_AND_PASSWORD)) {
            query.setString(1, login.toLowerCase());
            query.setString(2, password);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                User user = getUserFromResultSet(rs);
                result = Optional.of(user);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_USER_EXCEPTION_GET_BY_LOGIN_AND_PASSWORD);
            throw new DAOException(LoggingMessages.DAO_USER_EXCEPTION_GET_BY_LOGIN_AND_PASSWORD, ex);
        }
        return result;
    }

    @Override
    public List<User> getAllByRole(Role role) {
        List<User> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_ALL_BY_ROLE)) {
            query.setString(1, role.name());
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                result.add(user);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_USER_EXCEPTION_GET_ALL_BY_ROLE);
            throw new DAOException(LoggingMessages.DAO_USER_EXCEPTION_GET_ALL_BY_ROLE, ex);
        }
        return result;
    }

    @Override
    public Optional<User> getById(int id) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                User user = getUserFromResultSet(rs);
                result = Optional.of(user);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_USER_EXCEPTION_GET_BY_ID);
            throw new DAOException(LoggingMessages.DAO_USER_EXCEPTION_GET_BY_ID, ex);
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(User user) {
        try (PreparedStatement query = connection.prepareStatement(CREATE_USER)) {
            query.setString(1, user.getLogin());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getEmail());
            query.setString(5, user.getPassword());
            query.setInt(6, user.getRole().getId());
            query.setString(7, user.getStatus().name());
            query.executeUpdate();
            query.close();
        } catch (Exception ex) {
            logger.error(LoggingMessages.DAO_USER_EXCEPTION_USER_CREATING, ex);
            throw new DAOException(LoggingMessages.DAO_USER_EXCEPTION_USER_CREATING, ex);
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

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder().
                setId(rs.getInt(ID))
                .setLogin(rs.getString(LOGIN))
                .setFirstName(rs.getString(FIRST_NAME))
                .setLastName(rs.getString(LAST_NAME))
                .setEmail(rs.getString(EMAIL))
                .setPassword(rs.getString(PASSWORD))
                .setRole(Role.valueOf(rs.getString(USER_ROLE_NAME)))
                .setStatus(ActivationStatus.valueOf(rs.getString(STATUS)))
                .build();
    }

}
