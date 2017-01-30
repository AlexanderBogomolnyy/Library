package ua.training.library.service;

import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.states.Role;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getById(int id);
    List<Order> getAllAvailable(int userId, Role role);
    void create(Order order);
    void createNewOrder(Order order);
    void update(Order order);
    void delete(int id);

}
