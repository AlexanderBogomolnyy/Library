package ua.training.library.dao;

import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.states.BookStatus;

import java.util.List;

public interface CatalogDAO extends GenericDAO<Catalog> {
    List<Catalog> getByStatus(BookStatus status);
    List<Catalog> getByCategoryId(int categoryId);
}
