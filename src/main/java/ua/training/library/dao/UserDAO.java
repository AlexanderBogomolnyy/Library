package ua.training.library.dao;

import ua.training.library.model.entity.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {
    Optional<User> getByLoginAndPassword(String login, String password);
    Optional<User> getByLoginAndEmail(String login, String email);
}
