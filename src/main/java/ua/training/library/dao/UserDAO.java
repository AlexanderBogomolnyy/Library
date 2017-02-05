package ua.training.library.dao;

import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;

import java.util.List;
import java.util.Optional;

/**
 * <p> The User DAO interface.
 * This interface provides methods for working with user data in data base.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public interface UserDAO extends GenericDAO<User> {

    /**
     * This method looks for User with specific login and password in data base
     *
     * @param login - user login
     * @param password - encoded user password
     * @return - Optional<User> which contains user or null, if user have not been founded
     */
    Optional<User> getByLoginAndPassword(String login, String password);

    /**
     * This method looks for Users with specific role
     *
     * @param role - user login
     * @return - list of users with specific role
     */
    List<User> getAllByRole(Role role);
}
