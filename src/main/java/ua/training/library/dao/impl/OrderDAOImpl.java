package ua.training.library.dao.impl;

import ua.training.library.dao.OrderDAO;
import ua.training.library.dao.query.QueryResource;
import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.Category;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.ActivationStatus;
import ua.training.library.model.entity.states.BookStatus;
import ua.training.library.model.entity.states.OrderType;
import ua.training.library.model.entity.states.Role;
import ua.training.library.model.util.DateHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection;

    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> getByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.ORDER_GET_BY_USER_ID) ){
            query.setInt(1, userId);
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Order order = getOrderFromResultSet(rs);
                result.add(order);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Optional<Order> getById(int id) {
        Optional<Order> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.ORDER_GET_BY_ID) ){
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()){
                Order order = getOrderFromResultSet(rs);
                result = Optional.of(order);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.ORDER_GET_ALL) ){
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Order order = getOrderFromResultSet(rs);
                result.add(order);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    private Order getOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order.Builder()
                .setId(rs.getInt(QueryResource.ID))
                .setUser(new User.Builder()
                        .setId(rs.getInt(rs.getInt(QueryResource.ORDER_USER_ID)))
                        .setLogin(rs.getString(QueryResource.USER_LOGIN))
                        .setFirstName(rs.getString(QueryResource.USER_FIRST_NAME))
                        .setLastName(rs.getString(QueryResource.USER_LAST_NAME))
                        .setEmail(rs.getString(QueryResource.USER_EMAIL))
                        .setPassword(rs.getString(QueryResource.USER_PASSWORD))
                        .setRole(Role.valueOf(rs.getString(QueryResource.USER_ROLE_NAME)))
                        .setStatus(ActivationStatus.valueOf(rs.getString(QueryResource.USER_STATUS)))
                        .build())
                .setCatalog(new Catalog.Builder()
                        .setId(rs.getInt(QueryResource.BOOK_CATALOG_ID))
                        .setTitle(rs.getString(QueryResource.CATALOG_TITLE))
                        .setAuthor(rs.getString(QueryResource.CATALOG_AUTHOR))
                        .setYearOfPublication(rs.getInt(QueryResource.CATALOG_YEAR_OF_PUBLICATION))
                        .setCategory(new Category(rs.getInt(QueryResource.CATALOG_CATEGORY_ID),
                                rs.getString(QueryResource.CATEGORY_NAME)))
                        .setIsbn(rs.getString(QueryResource.CATALOG_ISBN))
                        .setAmountAll(rs.getInt(QueryResource.CATALOG_AMOUNT_ALL))
                        .setAmountAvailable(rs.getInt(QueryResource.CATALOG_AMOUNT_AVAILABLE))
                        .setStatus(BookStatus.valueOf(rs.getString(QueryResource.CATALOG_STATUS)))
                        .build())
                .setDateOfIssue(DateHelper.getLocalDate(rs.getDate(QueryResource.ORDER_DATE_OF_ISSUE)))
                .setExpectedDateOfReturn(DateHelper.getLocalDate(rs.getDate(QueryResource.ORDER_EXPECTED_DATE_OF_RETURN)))
                .setDateOfReturn(DateHelper.getLocalDate(rs.getDate(QueryResource.ORDER_DATE_OF_RETURN)))
                .setType(OrderType.valueOf(rs.getString(QueryResource.ORDER_ORDER_TYPE)))
                .build();
        return order;
    }

    @Override
    public void create(Order order) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(int id) {

    }
}
