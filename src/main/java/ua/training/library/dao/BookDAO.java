package ua.training.library.dao;

import ua.training.library.model.entity.Book;

import java.util.List;

public interface BookDAO extends GenericDAO<Book> {
    List<Book> getByCatalogId(int catalogId);
}
