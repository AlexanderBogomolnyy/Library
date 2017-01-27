package ua.training.library.dao;

import ua.training.library.model.entity.LibraryResponse;

import java.util.List;
import java.util.Optional;

public interface LibraryResponseDAO extends GenericDAO<LibraryResponse> {
    List<LibraryResponse> getByClientId(int userId);
    Optional<LibraryResponse> getByOrderId(int orderId);
}
