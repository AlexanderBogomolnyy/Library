package ua.training.library.dao;

import ua.training.library.model.entity.Order;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {
    List<Order> getAllByUserId(int userId);
    void createNewOrder(Order order);
}
