package ua.training.library.dao.impl;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.dao.BookDAO;
import ua.training.library.dao.exception.DAOException;
import ua.training.library.model.entity.Book;
import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.entity.states.BookStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p> The class which implements Book DAO interface,
 * and overrides it's methods.
 * This class uses MySQL queries for sending requests to the database.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class BookDAOImpl implements BookDAO {

    /**
     * Logger for BookDAOImpl class
     */
    private static final Logger logger = Logger.getLogger(BookDAOImpl.class);

    /**
     * The names of table fields
     */
    private static final String ID = "book.id";
    private static final String CATALOG_ID = "catalog.id";
    private static final String CATALOG_TITLE = "catalog.title";
    private static final String CATALOG_AUTHOR = "catalog.author";
    private static final String CATALOG_YEAR_OF_PUBLICATION = "catalog.year_of_publication";
    private static final String CATALOG_CATEGORY_ID = "catalog.category_id";
    private static final String CATALOG_ISBN = "catalog.isbn";
    private static final String LIBRARY_IDENTIFIER = "book.lib_identifier";
    private static final String STATE = "book.state";
    private static final String LOCATION = "book.location";
    private static final String STATUS = "book.status";

    /**
     * The queries to the data base
     */
    private static final String GET_ALL = "SELECT `book`.`id`, `catalog`.`id`, `catalog`.`title`, " +
            "`catalog`.`author`, `catalog`.`year_of_publication`, `catalog`.`category_id`, " +
            "`catalog`.`ISBN`, `book`.`lib_identifier`, `book`.`state`, `book`.`location`, `book`.`status` " +
            "FROM `book` " +
            "LEFT JOIN `library_db`.`catalog` ON `book`.`catalog_id` = `catalog`.`id` ";
    private static final String GET_LOCATED_IN_LIBRARY_BY_CATALOG_ID = GET_ALL +
            "WHERE `book`.`location`='LIBRARY' AND `catalog`.`id` = ?";
    private static final String GET_BY_ID = GET_ALL + "WHERE `book`.`id` = ?";

    /**
     * This field contains connection to the data base
     */
    private Connection connection;

    public BookDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> getLocatedInLibraryByCatalogId(int catalogId) {
        List<Book> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_LOCATED_IN_LIBRARY_BY_CATALOG_ID)) {
            query.setInt(1, catalogId);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Book book = getBookFromResultSet(rs);
                result.add(book);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_BOOK_EXCEPTION_GET_LOCATED_IN_LIBRARY);
            throw new DAOException(LoggingMessages.DAO_BOOK_EXCEPTION_GET_LOCATED_IN_LIBRARY, ex);
        }
        return result;
    }

    @Override
    public Optional<Book> getById(int id) {
        Optional<Book> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Book book = getBookFromResultSet(rs);
                result = Optional.of(book);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_BOOK_EXCEPTION_GET_BY_ID);
            throw new DAOException(LoggingMessages.DAO_ORDER_EXCEPTION_GET_BY_ID, ex);
        }
        return result;
    }

    @Override
    public List<Book> getAll() {
        List<Book> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_ALL)) {
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Book book = getBookFromResultSet(rs);
                result.add(book);
            }
        } catch (SQLException ex) {
            logger.error(LoggingMessages.DAO_BOOK_EXCEPTION_GET_ALL);
            throw new RuntimeException(LoggingMessages.DAO_BOOK_EXCEPTION_GET_ALL, ex);
        }
        return result;
    }

    private Book getBookFromResultSet(ResultSet rs) throws SQLException {
        return new Book.Builder()
                .setId(rs.getInt(ID))
                .setCatalog(new Catalog.Builder()
                        .setId(rs.getInt(CATALOG_ID))
                        .setTitle(rs.getString(CATALOG_TITLE))
                        .setAuthor(rs.getString(CATALOG_AUTHOR))
                        .setYearOfPublication(rs.getInt(CATALOG_YEAR_OF_PUBLICATION))
                        .setCategoryId(rs.getInt(CATALOG_CATEGORY_ID))
                        .setIsbn(rs.getString(CATALOG_ISBN))
                        .build())
                .setLibraryIdentifier(rs.getString(LIBRARY_IDENTIFIER))
                .setState(rs.getString(STATE))
                .setLocation(BookLocation.valueOf(rs.getString(LOCATION)))
                .setStatus(BookStatus.valueOf((rs.getString(STATUS))))
                .build();
    }

    @Override
    public void create(Book book) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Book book) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}
