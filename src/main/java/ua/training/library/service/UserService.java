package ua.training.library.service;

import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password);
    Optional<User> getById(int id);
    List<User> getAllClients(Role role);
    void create(User user);
    void update(User user);
    void delete(int id);
}
