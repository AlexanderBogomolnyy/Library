package ua.training.library.dao.impl;

import ua.training.library.dao.CategoryDAO;
import ua.training.library.dao.query.QueryResource;
import ua.training.library.model.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAOImpl implements CategoryDAO {

    private Connection connection;

    public CategoryDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Category> getByName(String name) {
        Optional<Category> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.CATEGORY_GET_BY_NAME) ){
            query.setString(1, name);
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                Category category = new Category(rs.getInt(QueryResource.ID), rs.getString(QueryResource.CATEGORY_NAME));
                result = Optional.of(category);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Optional<Category> getById(int id) {
        Optional<Category> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.CATEGORY_GET_BY_ID) ){
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                Category category = new Category(rs.getInt(QueryResource.ID), rs.getString(QueryResource.CATEGORY_NAME));
                result = Optional.of(category);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<Category> getAll() {
        List<Category> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.CATEGORY_GET_ALL) ){
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Category category = new Category(rs.getInt(QueryResource.ID), rs.getString(QueryResource.CATEGORY_NAME));;
                result.add(category);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void create(Category category) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(int id) {

    }
}
