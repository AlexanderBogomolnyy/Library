package ua.training.library.dao.impl;

import ua.training.library.dao.LibraryResponseDAO;
import ua.training.library.dao.query.QueryResource;
import ua.training.library.model.entity.*;
import ua.training.library.model.entity.states.*;
import ua.training.library.model.util.DateHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryResponseDAOImpl implements LibraryResponseDAO {

    private Connection connection;

    public LibraryResponseDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<LibraryResponse> getByClientId(int clientId) {
        List<LibraryResponse> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.LIBRARY_RESPONSE_GET_BY_CLIENT_ID) ){
            query.setInt(1, clientId);
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                LibraryResponse response = getLibraryResponseFromResultSet(rs);
                result.add(response);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Optional<LibraryResponse> getByOrderId(int orderId) {
        Optional<LibraryResponse> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.LIBRARY_RESPONSE_GET_BY_ORDER_ID) ){
            query.setInt(1, orderId);
            ResultSet rs = query.executeQuery();
            if (rs.next()){
                LibraryResponse response = getLibraryResponseFromResultSet(rs);
                result = Optional.of(response);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Optional<LibraryResponse> getById(int id) {
        Optional<LibraryResponse> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.LIBRARY_RESPONSE_GET_BY_ID) ){
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()){
                LibraryResponse response = getLibraryResponseFromResultSet(rs);
                result = Optional.of(response);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<LibraryResponse> getAll() {
        List<LibraryResponse> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.LIBRARY_RESPONSE_GET_ALL) ){
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                LibraryResponse response = getLibraryResponseFromResultSet(rs);
                result.add(response);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    private LibraryResponse getLibraryResponseFromResultSet(ResultSet rs) throws SQLException {
        User client = new User.Builder()
                .setId(rs.getInt(rs.getInt(QueryResource.CLIENT_ID)))
                .setLogin(rs.getString(QueryResource.CLIENT_LOGIN))
                .setFirstName(rs.getString(QueryResource.CLIENT_FIRST_NAME))
                .setLastName(rs.getString(QueryResource.CLIENT_LAST_NAME))
                .setEmail(rs.getString(QueryResource.CLIENT_EMAIL))
                .setPassword(rs.getString(QueryResource.CLIENT_PASSWORD))
                .setRole(Role.CLIENT)
                .setStatus(ActivationStatus.valueOf(rs.getString(QueryResource.CLIENT_STATUS)))
                .build();
        User librarian = new User.Builder()
                .setId(rs.getInt(rs.getInt(QueryResource.ORDER_USER_ID)))
                .setFirstName(rs.getString(QueryResource.USER_FIRST_NAME))
                .setLastName(rs.getString(QueryResource.USER_LAST_NAME))
                .setEmail(rs.getString(QueryResource.USER_EMAIL))
                .setPassword(rs.getString(QueryResource.USER_PASSWORD))
                .setRole(Role.LIBRARIAN)
                .setStatus(ActivationStatus.valueOf(rs.getString(QueryResource.USER_STATUS)))
                .build();
        Catalog catalog = new Catalog.Builder()
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
                .build();
        Book book = new Book.Builder()
                .setId(rs.getInt(QueryResource.BOOK_ID))
                .setCatalog(catalog)
                .setLibraryIdentifier(rs.getString(QueryResource.BOOK_LIBRARY_IDENTIFIER))
                .setState(rs.getString(rs.getString(QueryResource.BOOK_STATE)))
                .setLocation(BookLocation.valueOf(rs.getString(QueryResource.BOOK_LOCATION)))
                .setStatus(BookStatus.valueOf((rs.getString(QueryResource.BOOK_STATUS))))
                .build();
        Order order = new Order.Builder()
                .setId(rs.getInt(QueryResource.ORDER_ID))
                .setUser(client)
                .setCatalog(catalog)
                .setDateOfIssue(DateHelper.getLocalDate(rs.getDate(QueryResource.ORDER_DATE_OF_ISSUE)))
                .setExpectedDateOfReturn(DateHelper.getLocalDate(rs.getDate(QueryResource.ORDER_EXPECTED_DATE_OF_RETURN)))
                .setDateOfReturn(DateHelper.getLocalDate(rs.getDate(QueryResource.ORDER_DATE_OF_RETURN)))
                .setType(OrderType.valueOf(rs.getString(QueryResource.ORDER_ORDER_TYPE)))
                .build();
        LibraryResponse response = new LibraryResponse.Builder()
                .setId(rs.getInt(QueryResource.ID))
                .setOrder(order)
                .setProcessingDate(DateHelper.getLocalDate(rs.getDate(QueryResource.LIBRARY_RESPONSE_PROCESSING_DATE)))
                .setLibrarian(librarian)
                .setCatalog(catalog)
                .setBook(book)
                .setLocation(BookLocation.valueOf(QueryResource.LIBRARY_RESPONSE_BOOK_LOCATION))
                .setDateOfReturn(DateHelper.getLocalDate(rs.getDate(QueryResource.LIBRARY_RESPONSE_DATE_OF_RETURN)))
                .build();
        return response;
    }

    @Override
    public void create(LibraryResponse libraryResponse) {

    }

    @Override
    public void update(LibraryResponse libraryResponse) {

    }

    @Override
    public void delete(int id) {

    }
}
