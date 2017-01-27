package ua.training.library.dao;

import ua.training.library.model.entity.Category;

import java.util.Optional;

public interface CategoryDAO extends GenericDAO<Category> {
    Optional<Category> getByName(String name);
}
