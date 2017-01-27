package ua.training.library.dao.impl;

import ua.training.library.dao.CatalogDAO;
import ua.training.library.dao.query.QueryResource;
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

public class CatalogDAOImpl implements CatalogDAO {

    private Connection connection;

    public CatalogDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Catalog> getByStatus(BookStatus status) {
        List<Catalog> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.CATALOG_GET_BY_STATUS) ) {
            query.setString(1, status.name());
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Catalog catalog = getCatalogFromResultSet(rs);
                result.add(catalog);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<Catalog> getByCategoryId(int categoryId) {
        List<Catalog> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.CATALOG_GET_BY_CATEGORY_ID) ) {
            query.setInt(1, categoryId);
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Catalog catalog = getCatalogFromResultSet(rs);
                result.add(catalog);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Optional<Catalog> getById(int id) {
        Optional<Catalog> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.CATALOG_GET_BY_ID) ){
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                Catalog catalog = getCatalogFromResultSet(rs);
                result.of(catalog);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<Catalog> getAll() {
        List<Catalog> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.CATALOG_GET_ALL) ) {
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Catalog catalog = getCatalogFromResultSet(rs);
                result.add(catalog);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    private Catalog getCatalogFromResultSet(ResultSet rs) throws SQLException {
        Catalog catalog = new Catalog.Builder()
                .setId(rs.getInt(QueryResource.ID))
                .setTitle(rs.getString(QueryResource.CATALOG_TITLE))
                .setAuthor(rs.getString(QueryResource.CATALOG_AUTHOR))
                .setYearOfPublication(rs.getInt(QueryResource.CATALOG_YEAR_OF_PUBLICATION))
                .setCategory(new Category(rs.getInt(QueryResource.CATALOG_CATEGORY_ID),
                        rs.getString(QueryResource.CATEGORY_NAME)))
                .setIsbn(rs.getString(QueryResource.CATALOG_ISBN))
                .setAmountAll(rs.getInt(QueryResource.CATALOG_AMOUNT_ALL))
                .setAmountAvailable(rs.getInt(QueryResource.CATALOG_AMOUNT_AVAILABLE))
                .setStatus(BookStatus.valueOf(rs.getString(QueryResource.STATUS)))
                .build();
        return catalog;
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
