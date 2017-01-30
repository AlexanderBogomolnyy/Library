package ua.training.library.dao;

import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {
    Optional<User> getByLoginAndPassword(String login, String password);
    List<User> getAllByRole(Role role);
}
