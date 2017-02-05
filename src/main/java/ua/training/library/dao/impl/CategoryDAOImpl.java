package ua.training.library.dao.impl;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.dao.CategoryDAO;
import ua.training.library.dao.exception.DAOException;
import ua.training.library.model.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p> The class which implements Category DAO interface,
 * and overrides it's methods.
 * This class uses MySQL queries for sending requests to the database.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class CategoryDAOImpl implements CategoryDAO {

    /**
     * Logger for CategoryDAOImpl class
     */
    private static final Logger logger = Logger.getLogger(CategoryDAOImpl.class);

    /**
     * The names of table fields
     */
    private static final String ID = "category.id";
    private static final String NAME = "category.name";

    /**
     * The requests to the data base
     */
    private static final String GET_ALL = "SELECT * FROM `category` ";
    private static final String GET_BY_NAME = GET_ALL + "WHERE `category`.`name` = ?";
    private static final String GET_BY_ID = GET_ALL + "WHERE `category`.`id` = ?";

    /**
     * This field contains connection to the data base
     */
    private Connection connection;

    public CategoryDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Category> getByName(String name) {
        Optional<Category> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(GET_BY_NAME) ){
            query.setString(1, name);
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                Category category = new Category(rs.getInt(ID), rs.getString(NAME));
                result = Optional.of(category);
            }
        }catch(SQLException ex){
            logger.error(LoggingMessages.DAO_CATEGORY_EXCEPTION_GET_BY_NAME);
            throw new DAOException(LoggingMessages.DAO_CATEGORY_EXCEPTION_GET_BY_NAME);
        }
        return result;
    }

    @Override
    public Optional<Category> getById(int id) {
        Optional<Category> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(GET_BY_ID) ){
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                Category category = new Category(rs.getInt(ID), rs.getString(NAME));
                result = Optional.of(category);
            }
        }catch(SQLException ex){
            logger.error(LoggingMessages.DAO_CATEGORY_EXCEPTION_GET_BY_ID);
            throw new DAOException(LoggingMessages.DAO_CATEGORY_EXCEPTION_GET_BY_ID, ex);
        }
        return result;
    }

    @Override
    public List<Category> getAll() {
        List<Category> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(GET_ALL) ){
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Category category = new Category(rs.getInt(ID), rs.getString(NAME));
                result.add(category);
            }
        }catch(SQLException ex){
            logger.error(LoggingMessages.DAO_CATEGORY_EXCEPTION_GET_ALL);
            throw new DAOException(LoggingMessages.DAO_CATEGORY_EXCEPTION_GET_ALL, ex);
        }
        return result;
    }

    @Override
    public void create(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}
