package ua.training.library.dao.impl;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.dao.CatalogDAO;
import ua.training.library.dao.exception.DAOException;
import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.Category;
import ua.training.library.model.entity.states.BookStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p> The class which implements Catalog DAO interface,
 * and overrides it's methods.
 * This class uses MySQL queries for sending requests to the database.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class CatalogDAOImpl implements CatalogDAO {

    /**
     * Logger for CatalogDAOImpl class
     */
    private static final Logger logger = Logger.getLogger(CatalogDAOImpl.class);

    /**
     * The names of table fields
     */
    private static final String ID = "catalog.id";
    private static final String TITLE= "catalog.title";
    private static final String AUTHOR = "catalog.author";
    private static final String YEAR_OF_PUBLICATION = "catalog.year_of_publication";
    private static final String ISBN = "catalog.ISBN";
    private static final String AMOUNT_ALL = "catalog.amount_all";
    private static final String AMOUNT_AVAILABLE = "catalog.amount_available";
    private static final String STATUS = "catalog.status";
    private static final String CATEGORY_ID = "category.id";
    private static final String CATEGORY_NAME = "category.name";

    /**
     * The queries to the data base
     */
    private static final String GET_ALL = "SELECT * FROM `catalog` " +
            "JOIN `category` ON `catalog`.`category_id` = `category`.`id` ";
    private static final String GET_BY_ID = GET_ALL + "WHERE `catalog`.`id` = ?";
    private static final String GET_BY_STATUS = GET_ALL + "WHERE `catalog`.`status` = ?";
    private static final String GET_BY_CATEGORY_ID = GET_ALL + "WHERE `catalog`.`category_id` = ?";

    /**
     * This field contains connection to the data base
     */
    private Connection connection;

    public CatalogDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Catalog> getByStatus(BookStatus status) {
        List<Catalog> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(GET_BY_STATUS) ) {
            query.setString(1, status.name());
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Catalog catalog = getCatalogFromResultSet(rs);
                result.add(catalog);
            }
        }catch(SQLException ex){
            logger.error(LoggingMessages.DAO_CATALOG_EXCEPTION_GET_BY_STATUS);
            throw new DAOException(LoggingMessages.DAO_CATALOG_EXCEPTION_GET_BY_STATUS, ex);
        }
        return result;
    }

    @Override
    public List<Catalog> getByCategoryId(int categoryId) {
        List<Catalog> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(GET_BY_CATEGORY_ID) ) {
            query.setInt(1, categoryId);
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Catalog catalog = getCatalogFromResultSet(rs);
                result.add(catalog);
            }
        }catch(SQLException ex){
            logger.error(LoggingMessages.DAO_CATALOG_EXCEPTION_GET_BY_CATEGORY_ID);
            throw new DAOException(LoggingMessages.DAO_CATALOG_EXCEPTION_GET_BY_CATEGORY_ID, ex);
        }
        return result;
    }

    @Override
    public Optional<Catalog> getById(int id) {
        Optional<Catalog> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(GET_BY_ID) ){
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                Catalog catalog = getCatalogFromResultSet(rs);
                result = Optional.of(catalog);
            }
        }catch(SQLException ex){
            logger.error(LoggingMessages.DAO_CATALOG_EXCEPTION_GET_BY_ID);
            throw new DAOException(LoggingMessages.DAO_CATALOG_EXCEPTION_GET_BY_ID, ex);
        }
        return result;
    }

    @Override
    public List<Catalog> getAll() {
        List<Catalog> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(GET_ALL) ) {
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Catalog catalog = getCatalogFromResultSet(rs);
                result.add(catalog);
            }
        }catch(SQLException ex){
            logger.error(LoggingMessages.DAO_CATALOG_EXCEPTION_GET_ALL);
            throw new DAOException(LoggingMessages.DAO_CATALOG_EXCEPTION_GET_ALL, ex);
        }
        return result;
    }

    private Catalog getCatalogFromResultSet(ResultSet rs) throws SQLException {
        return new Catalog.Builder()
                .setId(rs.getInt(ID))
                .setTitle(rs.getString(TITLE))
                .setAuthor(rs.getString(AUTHOR))
                .setYearOfPublication(rs.getInt(YEAR_OF_PUBLICATION))
                .setCategoryId(rs.getInt(CATEGORY_ID))
                .setCategory(new Category(rs.getInt(CATEGORY_ID), rs.getString(CATEGORY_NAME)))
                .setIsbn(rs.getString(ISBN))
                .setAmountAll(rs.getInt(AMOUNT_ALL))
                .setAmountAvailable(rs.getInt(AMOUNT_AVAILABLE))
                .setStatus(BookStatus.valueOf(rs.getString(STATUS)))
                .build();
    }

    @Override
    public void create(Catalog catalog) {

    }

    @Override
    public void update(Catalog catalog) {

    }

    @Override
    public void delete(int id) {

    }
}
