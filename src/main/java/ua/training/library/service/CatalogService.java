package ua.training.library.service;


import ua.training.library.model.entity.Catalog;

import java.util.List;
import java.util.Optional;

public interface CatalogService {
    Optional<Catalog> getById(int id);
    List<Catalog> getAll();
    void create(Catalog catalog);
    void update(Catalog catalog);
    void delete(int id);
}
