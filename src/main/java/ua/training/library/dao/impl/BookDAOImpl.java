package ua.training.library.dao.impl;

import ua.training.library.dao.BookDAO;
import ua.training.library.dao.query.QueryResource;
import ua.training.library.model.entity.Book;
import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.Category;
import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.entity.states.BookStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAOImpl implements BookDAO {

    private Connection connection;

    public BookDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> getByCatalogId(int catalogId) {
        return null;
    }

    @Override
    public Optional<Book> getById(int id) {
        Optional<Book> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.BOOK_GET_BY_ID) ){
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                Book book = getBookFromResultSet(rs);
                result = Optional.of(book);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<Book> getAll() {
        List<Book> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.BOOK_GET_ALL) ){
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Book book = getBookFromResultSet(rs);
                result.add(book);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    private Book getBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book.Builder()
                .setId(rs.getInt(QueryResource.ID))
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
                .setLibraryIdentifier(rs.getString(QueryResource.BOOK_LIBRARY_IDENTIFIER))
                .setState(rs.getString(rs.getString(QueryResource.BOOK_STATE)))
                .setLocation(BookLocation.valueOf(rs.getString(QueryResource.BOOK_LOCATION)))
                .setStatus(BookStatus.valueOf((rs.getString(QueryResource.BOOK_STATUS))))
                .build();
        return book;
    }

    @Override
    public void create(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(int id) {

    }
}
