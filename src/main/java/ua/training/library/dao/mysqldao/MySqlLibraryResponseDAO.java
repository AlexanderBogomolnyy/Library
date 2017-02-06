package ua.training.library.dao.mysqldao;

import org.apache.log4j.Logger;
import ua.training.library.messages.LoggingMessages;
import ua.training.library.dao.LibraryResponseDAO;
import ua.training.library.dao.exception.DAOException;
import ua.training.library.model.entity.*;
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
 * <p> The class which implements Library Response DAO interface,
 * and overrides it's methods.
 * This class uses MySQL queries for sending requests to the database.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class MySqlLibraryResponseDAO implements LibraryResponseDAO {

    /**
     * Logger for LibraryResponseDAOImpl class
     */
    private static final Logger logger = Logger.getLogger(MySqlLibraryResponseDAO.class);

    /**
     * The names of table fields
     */
    private static final String ID = "lib_response.id";
    private static final String ORDER_ID = "lib_response.order_id";
    private static final String ORDER_TYPE = "order.order_type";
    private static final String PROCESSING_DATE = "lib_response.processing_date";
    private static final String LIBRARIAN_ID = "lib_response.librarian_id";
    private static final String CATALOG_ID = "lib_response.catalog_id";
    private static final String BOOK_ID = "lib_response.book_id";
    private static final String BOOK_LOCATION = "lib_response.book_location";
    private static final String DATE_OF_RETURN = "lib_response.date_of_return";
    private static final String CATALOG_TITLE = "catalog.title";
    private static final String CATALOG_AUTHOR = "catalog.author";
    private static final String BOOK_LIBRARY_IDENTIFIER = "book.lib_identifier";
    private static final String CLIENT_ID = "client.id";
    private static final String CLIENT_FIRST_NAME = "client.f_name";
    private static final String CLIENT_LAST_NAME = "client.l_name";

    /**
     * The queries to the data base
     */
    private static final String GET_ALL = "SELECT `lib_response`.`id`, `lib_response`.`order_id`, `lib_response`.`processing_date`, " +
            "`lib_response`.`librarian_id`, `lib_response`.`catalog_id`, `lib_response`.`book_id`, " +
            "`lib_response`.`book_location`, `lib_response`.`date_of_return`, `catalog`.`title`, `catalog`.`author`, " +
            "`book`.`lib_identifier`, `client`.`id`, `client`.`f_name`, `client`.`l_name`, `order`.`order_type` " +
            "FROM `lib_response` " +
            "JOIN `order` ON `order_id` = `order`.`id` " +
            "JOIN `user` AS `client` ON `order`.`user_id` = `client`.`id` " +
            "JOIN `catalog` ON `order`.`catalog_id` = `catalog`.`id` " +
            "JOIN `book` ON `book_id` = `book`.`id` ";
    private static final String GET_BY_ID = GET_ALL + "WHERE `lib_response`.`id` = ?";
    private static final String GET_BY_LIBRARIAN_ID = GET_ALL + "WHERE `lib_response`.`librarian_id` = ?";
    private static final String GET_BY_ORDER_ID = GET_ALL + "WHERE `order`.`id` = ?";
    private static final String ORDER_BY = " ORDER BY `order`.`order_type`, `lib_response`.`processing_date` ";
    private static final String CREATE_NEW_LIBRARY_RESPONSE = "INSERT INTO `lib_response` " +
            "(`order_id`, `processing_date`, `librarian_id`, `catalog_id`, `book_id`, `book_location`) " +
            "VALUES (?, ?, ?, ?, ?, ?); ";
    private static final String UPDATE_ORDER_AFTER_CREATE_RESPONSE = "UPDATE `order` " +
            "INNER JOIN `catalog` ON `catalog`.`id` = `order`.`catalog_id` " +
            "INNER JOIN `book` ON `book`.`id` = ? " +
            "SET `order`.`order_type` = \'" + OrderType.IN_PROCESSING.name() + "\', " +
            "`order`.`expected_book_location` = ?, " +
            "`catalog`.`amount_available` = `catalog`.`amount_available` - 1, " +
            "`book`.`location` = ? " +
            "WHERE `order`.`id` = ? ";
    private static final String UPDATE_FOR_COMPLETE = "UPDATE `lib_response` " +
            "JOIN `order` ON `lib_response`.`order_id` = `order`.`id` " +
            "JOIN `book` ON `lib_response`. `book_id` = `book`.`id` " +
            "JOIN `catalog` ON `book`.`catalog_id` = `catalog`.`id` " +
            "SET `lib_response`.`date_of_return` = ?, " +
            "`order`.`date_of_return` = ?, " +
            "`order`.`order_type` = \'" + OrderType.COMPLETED.name() + "\', " +
            "`book`.`location` = \'" + BookLocation.LIBRARY.name() + "\', " +
            "`catalog`.`amount_available` = `catalog`.`amount_available` + 1 " +
            "WHERE `order`.`order_type` = \'" + OrderType.IN_PROCESSING + "\' AND `lib_response`.`id` = ?";

    /**
     * This field contains connection to the data base
     */
    private Connection connection;

    public MySqlLibraryResponseDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<LibraryResponse> getByLibrarianId(int librarianId) {
        List<LibraryResponse> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_LIBRARIAN_ID + ORDER_BY)) {
            query.setInt(1, librarianId);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                LibraryResponse response = getLibraryResponseFromResultSet(rs);
                result.add(response);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_GET_BY_LIBRARIAN_ID);
            throw new DAOException(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_GET_BY_LIBRARIAN_ID, ex);
        }
        return result;
    }

    @Override
    public Optional<LibraryResponse> getByOrderId(int orderId) {
        Optional<LibraryResponse> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ORDER_ID)) {
            query.setInt(1, orderId);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                LibraryResponse response = getLibraryResponseFromResultSet(rs);
                result = Optional.of(response);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_GET_BY_ORDER_ID);
            throw new DAOException(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_GET_BY_ORDER_ID, ex);
        }
        return result;
    }

    @Override
    public void createNewLibraryResponseWithUpdateOrder(LibraryResponse response) {
        try (PreparedStatement queryCreate = connection.prepareStatement(CREATE_NEW_LIBRARY_RESPONSE);
             PreparedStatement queryUpdate = connection.prepareStatement(UPDATE_ORDER_AFTER_CREATE_RESPONSE)) {
            createNewLibraryResponse(response, queryCreate);
            updateOrderAfterCreateLibraryResponse(response, queryUpdate);
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_CREATE_NEW_RESPONSE, ex);
            throw new DAOException(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_CREATE_NEW_RESPONSE, ex);
        }
    }

    private void createNewLibraryResponse(LibraryResponse response, PreparedStatement query) throws SQLException {
        query.setInt(1, response.getOrderId());
        query.setDate(2, DateHelper.getDate(response.getProcessingDate()));
        query.setInt(3, response.getLibrarianID());
        query.setInt(4, response.getCatalogId());
        query.setInt(5, response.getBookId());
        query.setString(6, response.getLocation().name());
        query.executeUpdate();
    }

    private void updateOrderAfterCreateLibraryResponse(LibraryResponse response, PreparedStatement query) throws SQLException {
        query.setInt(1, response.getBookId());
        query.setString(2, response.getLocation().name());
        query.setString(3, response.getLocation().name());
        query.setInt(4, response.getOrderId());
        query.executeUpdate();
    }

    @Override
    public int updateAndCompleteLibraryResponse(int responseId, LocalDate dateOfReturn) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE_FOR_COMPLETE)) {
            query.setDate(1, DateHelper.getDate(dateOfReturn));
            query.setDate(2, DateHelper.getDate(dateOfReturn));
            query.setInt(3, responseId);
            return query.executeUpdate();
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_ORDER_EXCEPTION_UPDATE_AND_CLOSE);
            throw new DAOException(LoggingMessages.DAO_ORDER_EXCEPTION_UPDATE_AND_CLOSE, ex);
        }
    }
    @Override
    public Optional<LibraryResponse> getById(int id) {
        Optional<LibraryResponse> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                LibraryResponse response = getLibraryResponseFromResultSet(rs);
                result = Optional.of(response);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_GET_BY_ID);
            throw new DAOException(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_GET_BY_ID, ex);
        }
        return result;
    }

    @Override
    public List<LibraryResponse> getAll() {
        List<LibraryResponse> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_ALL + ORDER_BY)) {
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                LibraryResponse response = getLibraryResponseFromResultSet(rs);
                result.add(response);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_GET_ALL);
            throw new DAOException(LoggingMessages.DAO_LIBRARY_RESPONSE_EXCEPTION_GET_ALL, ex);
        }
        return result;
    }

    private LibraryResponse getLibraryResponseFromResultSet(ResultSet rs) throws SQLException {
        return new LibraryResponse.Builder()
                .setId(rs.getInt(ID))
                .setOrderId(rs.getInt(ORDER_ID))
                .setOrder(new Order.Builder()
                        .setId(rs.getInt(ORDER_ID))
                        .setUser(new User.Builder()
                                .setId(rs.getInt(CLIENT_ID))
                                .setFirstName(rs.getString(CLIENT_FIRST_NAME))
                                .setLastName(rs.getString(CLIENT_LAST_NAME))
                                .build())
                        .setType(OrderType.valueOf(rs.getString(ORDER_TYPE)))
                        .build())
                .setProcessingDate(DateHelper.getLocalDate(rs.getDate(PROCESSING_DATE)))
                .setLibrarianId(rs.getInt(LIBRARIAN_ID))
                .setCatalogId(rs.getInt(CATALOG_ID))
                .setCatalog(new Catalog.Builder()
                        .setId(rs.getInt(CATALOG_ID))
                        .setTitle(rs.getString(CATALOG_TITLE))
                        .setAuthor(rs.getString(CATALOG_AUTHOR))
                        .build())
                .setBookId(rs.getInt(BOOK_ID))
                .setBookLibraryIdentifier(rs.getString(BOOK_LIBRARY_IDENTIFIER))
                .setLocation(BookLocation.valueOf(rs.getString(BOOK_LOCATION)))
                .setDateOfReturn(DateHelper.getLocalDate(rs.getDate(DATE_OF_RETURN)))
                .build();
    }

    @Override
    public void create(LibraryResponse libraryResponse) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(LibraryResponse libraryResponse) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}
