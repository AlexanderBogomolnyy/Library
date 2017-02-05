package ua.training.library.dao;

import ua.training.library.model.entity.LibraryResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * <p> The Library response DAO interface.
 * This interface provides methods for working with library responses data in data base.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public interface LibraryResponseDAO extends GenericDAO<LibraryResponse> {

    /**
     * This method looks for library responses created by librarian with specific librarian id
     *
     * @param librarianId - librarian id
     * @return list of librarian responses
     */
    List<LibraryResponse> getByLibrarianId(int librarianId);

    /**
     * This method looks for librarian response based on specific order id
     *
     * @param orderId - order id
     * @return library response
     */
    Optional<LibraryResponse> getByOrderId(int orderId);

    /**
     * This method writes down new library response into data base
     *
     * @param response - library response for saving
     */
    void createNewLibraryResponseWithUpdateOrder(LibraryResponse response);

    /**
     * This method update library response and associated order date of return
     * and change order type to COMPLETED
     * Although this method changed book example location to LIBRARY
     * and reduce the amount of available books in catalog
     *
     * @param responseId - response ID
     * @param dateOfReturn - response and order date of return
     *                     (this parameter have to be same for both)
     * @return the number of updated rows
     */
    int updateAndCompleteLibraryResponse(int responseId, LocalDate dateOfReturn);
}
