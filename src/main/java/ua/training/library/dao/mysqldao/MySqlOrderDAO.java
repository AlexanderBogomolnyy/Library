package ua.training.library.dao.mysqldao;

import org.apache.log4j.Logger;
import ua.training.library.messages.LoggingMessages;
import ua.training.library.dao.OrderDAO;
import ua.training.library.dao.exception.DAOException;
import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.*;
import ua.training.library.model.util.DateHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p> The class which implements Order DAO interface,
 * and overrides it's methods.
 * This class uses MySQL queries for sending requests to the database.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class MySqlOrderDAO implements OrderDAO {

    /**
     * Logger for OrderDAOImpl class
     */
    private static final Logger logger = Logger.getLogger(MySqlOrderDAO.class);

    /**
     * The names of table fields
     */
    private static final String ID = "order.id";
    private static final String USER_ID = "user.id";
    private static final String USER_FIRST_NAME = "user.f_name";
    private static final String USER_LAST_NAME = "user.l_name";
    private static final String CATALOG_ID = "catalog.id";
    private static final String CATALOG_TITLE = "catalog.title";
    private static final String CATALOG_AUTHOR = "catalog.author";
    private static final String DATE_OF_ISSUE = "order.date_of_issue";
    private static final String EXPECTED_DATE_OF_RETURN = "order.expected_date_of_return";
    private static final String DATE_OF_RETURN = "order.date_of_return";
    private static final String ORDER_TYPE = "order.order_type";
    private static final String EXPECTED_BOOK_LOCATION = "order.expected_book_location";

    /**
     * The queries to the data base
     */
    private static final String GET_ALL =
            "SELECT `order`.`id`, `user`.`id`, `user`.`f_name`, `user`.`l_name`, `catalog`.`id`, `catalog`.`title`, " +
                    "`catalog`.`author`, `order`.`date_of_issue`,  `order`.`expected_date_of_return`, `order`.`date_of_return`, " +
                    "`order`.`order_type`, `order`.`expected_book_location` " +
                    "FROM `order` LEFT JOIN `user` ON (`order`.`user_id` = `user`.`id`) " +
                    "LEFT JOIN `catalog` ON (`order`.`catalog_id` = `catalog`.`id`) ";
    private static final String GET_BY_ID = GET_ALL + "WHERE `order`.`id` = ? ";
    private static final String GET_ALL_BY_USER_ID = GET_ALL + "WHERE `order`.`user_id` = ? ";
    private static final String CREATE_NEW_ORDER = "INSERT INTO `order` " +
            "(`user_id`, `catalog_id`, `date_of_issue`, `expected_date_of_return`, `order_type`, `expected_book_location`) " +
            "VALUES (?, ?, ?, ?, '" + OrderType.NEW.name() + "', ?)";
    private static final String SORTING = "ORDER BY `order_type`, `date_of_issue`";
    private static final String UPDATE_FOR_CLOSING = "UPDATE `order` SET `order`.`date_of_return` = ?, " +
            "`order`.`order_type` = \'" + OrderType.COMPLETED.name() + "\' " +
            "WHERE `order`.`id` = ?";

    /**
     * This field contains connection to the data base
     */
    private Connection connection;

    public MySqlOrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> getAllByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_ALL_BY_USER_ID + SORTING)) {
            query.setInt(1, userId);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                result.add(order);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_ORDER_EXCEPTION_GET_BY_USER_ID);
            throw new DAOException(LoggingMessages.DAO_ORDER_EXCEPTION_GET_BY_USER_ID, ex);
        }
        return result;
    }

    @Override
    public void createNewOrder(Order order) {
        try (PreparedStatement query = connection.prepareStatement(CREATE_NEW_ORDER)) {
            query.setInt(1, order.getUserId());
            query.setInt(2, order.getCatalogId());
            query.setDate(3, DateHelper.getDate(order.getDateOfIssue()));
            query.setDate(4, DateHelper.getDate(order.getExpectedDateOfReturn()));
            query.setString(5, order.getExpectedBookLocation().name());
            query.executeUpdate();
            query.close();
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_ORDER_EXCEPTION_CREATE_NEW_ORDER);
            throw new DAOException(LoggingMessages.DAO_ORDER_EXCEPTION_CREATE_NEW_ORDER, ex);
        }
    }

    @Override
    public void updateAndComplete(int orderId, LocalDate dateOfReturn) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE_FOR_CLOSING)) {
            query.setDate(1, DateHelper.getDate(dateOfReturn));
            query.setInt(2, orderId);
            query.executeUpdate();
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_ORDER_EXCEPTION_UPDATE_AND_CLOSE);
            throw new DAOException(LoggingMessages.DAO_ORDER_EXCEPTION_UPDATE_AND_CLOSE, ex);
        }
    }

    @Override
    public Optional<Order> getById(int id) {
        Optional<Order> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                result = Optional.of(order);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_ORDER_EXCEPTION_GET_BY_ID);
            throw new DAOException(LoggingMessages.DAO_ORDER_EXCEPTION_GET_BY_ID, ex);
        }
        return result;
    }

    @Override
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_ALL + SORTING)) {
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                result.add(order);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_ORDER_EXCEPTION_GET_ALL);
            throw new DAOException(LoggingMessages.DAO_ORDER_EXCEPTION_GET_ALL, ex);
        }
        return result;
    }

    private Order getOrderFromResultSet(ResultSet rs) throws SQLException {
        return new Order.Builder()
                .setId(rs.getInt(ID))
                .setUserID(rs.getInt(USER_ID))
                .setUser(new User.Builder()
                        .setId(rs.getInt(USER_ID))
                        .setFirstName(rs.getString(USER_FIRST_NAME))
                        .setLastName(rs.getString(USER_LAST_NAME))
                        .build())
                .setCatalogID(rs.getInt(CATALOG_ID))
                .setCatalog(new Catalog.Builder()
                        .setId(rs.getInt(CATALOG_ID))
                        .setTitle(rs.getString(CATALOG_TITLE))
                        .setAuthor(rs.getString(CATALOG_AUTHOR))
                        .build())
                .setDateOfIssue(DateHelper.getLocalDate(rs.getDate(DATE_OF_ISSUE)))
                .setExpectedDateOfReturn(DateHelper.getLocalDate(rs.getDate(EXPECTED_DATE_OF_RETURN)))
                .setDateOfReturn(DateHelper.getLocalDate(rs.getDate(DATE_OF_RETURN)))
                .setType(OrderType.valueOf(rs.getString(ORDER_TYPE)))
                .setExpectedBookLocation(BookLocation.valueOf(rs.getString(EXPECTED_BOOK_LOCATION)))
                .build();
    }

    @Override
    public void create(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}
