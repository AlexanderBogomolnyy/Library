package ua.training.library.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<E> {
    Optional<E> getById(int id);
    List<E> getAll();
    void create(E e);
    void update(E e);
    void delete(int id);
}

