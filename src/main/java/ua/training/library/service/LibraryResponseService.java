package ua.training.library.service;

import ua.training.library.model.entity.LibraryResponse;
import ua.training.library.model.entity.states.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LibraryResponseService {
    List<LibraryResponse> getAvailableForUser(int userId, Role role);
    void createNewLibraryResponse(LibraryResponse response, Role role);
    void completeLibraryResponseByCurrentDate(int responseID, Role role);
    Optional<LibraryResponse> getById(int id);
    List<LibraryResponse> getAll();
    void create(LibraryResponse response);
    void update(LibraryResponse response);
    void delete(int id);
}
