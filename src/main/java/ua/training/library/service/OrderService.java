package ua.training.library.service;

import ua.training.library.model.entity.Book;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.states.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getById(int id);
    List<Book> getBooksForOrder(Order order);
    List<Order> getAllAvailable(int userId, Role role);
    void create(Order order);
    void createNewOrder(Order order, Role role);
    void updateAndComplete(int orderId, LocalDate dateOfReturn);
    void update(Order order);
    void delete(int id);
}
