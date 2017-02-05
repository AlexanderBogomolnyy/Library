package ua.training.library.dao;

import ua.training.library.model.entity.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * <p> The Order DAO interface.
 * This interface provides methods for working with order data in data base.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public interface OrderDAO extends GenericDAO<Order> {

    /**
     * This method looks for orders made by user with specific ID
     *
     * @param userId - user ID
     * @return - list of orders
     */
    List<Order> getAllByUserId(int userId);

    /**
     * This method writes down new order into data base
     *
     * @param order - order for saving
     */
    void createNewOrder(Order order);

    /**
     * This method updates order parameters, sets date of returning book to the library
     * and changes order status into "COMPLETED"
     * @param orderId - order id
     * @param dateOfReturn - date of return the book to the library
     */
    void updateAndComplete(int orderId, LocalDate dateOfReturn);
}
