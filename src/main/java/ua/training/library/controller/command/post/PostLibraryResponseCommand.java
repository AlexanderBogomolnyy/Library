package ua.training.library.controller.command.post;

import org.apache.log4j.Logger;
import ua.training.library.messages.ServiceMessages;
import ua.training.library.messages.LoggingMessages;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.controller.validator.DateStringValidator;
import ua.training.library.controller.validator.EnumValidator;
import ua.training.library.controller.validator.NumberStringValidator;
import ua.training.library.model.entity.LibraryResponse;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.util.DateHelper;
import ua.training.library.service.LibraryResponseService;
import ua.training.library.service.basic.BasicLibraryResponseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ua.training.library.controller.configuration.Parameters.*;

public class PostLibraryResponseCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(PostLibraryResponseCommand.class);

    private static final String INVALID_BOOK_LOCATION = "invalid.book_location";
    private static final String INVALID_PROCESSING_DATE = "invalid.processing.date";

    private LibraryResponseService service = BasicLibraryResponseService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> errors = new ArrayList<>();
        User loggedInUser = getUserFromSession(request);
        LibraryResponse libraryResponse = parseRequestData(request, errors);
        libraryResponse.setLibrarianID(loggedInUser.getId());
        if (errors.isEmpty()) {
            try {
                service.createNewLibraryResponse(libraryResponse, loggedInUser.getRole());
                setSuccessMessageToSession(request, ServiceMessages.SUCCESS_IN_CREATING_LIBRARY_RESPONSE);
                response.sendRedirect(getBaseUrlByRole(request) + Paths.LIBRARY_RESPONSES);
                return Pages.REDIRECT;
            } catch (RuntimeException ex) {
                errors.add(ex.getMessage());
                logger.error(LoggingMessages.COMMAND_ERROR_IN_LIBRARY_RESPONSE_CREATING, ex);
            }
        }
        //TODO localize error messages
        setErrorMessageToSession(request, Arrays.toString(errors.toArray()));
        response.sendRedirect(getBaseUrlByRole(request) + Paths.ORDERS +
                Paths.DELIMITER + getIdFromRequestURI(request) + Paths.LIBRARY_RESPONSE);
        return Pages.REDIRECT;
    }

    private LibraryResponse parseRequestData(HttpServletRequest request, List<String> errors) {
        return new LibraryResponse.Builder()
                .setOrderId(getIdFromRequestURI(request))
                .setBookId(getValidInteger(request.getParameter(BOOK_EXAMPLE), errors))
                .setProcessingDate(getValidDate(request.getParameter(PROCESSING_DATE), errors))
                .setLocation(getValidBookLocationEnum(request.getParameter(BOOK_LOCATION), errors))
                .setCatalogId(getValidInteger(request.getParameter(CATALOG_ID), errors))
                .build();
    }

    private BookLocation getValidBookLocationEnum(String parameter, List<String> errors) {
        EnumValidator validator = new EnumValidator(BookLocation.class, INVALID_BOOK_LOCATION);
        if (validator.validate(parameter))
            return BookLocation.valueOf(parameter);
        else {
            errors.add(validator.getErrorMessage());
            return BookLocation.ON_HAND;
        }
    }

    private int getValidInteger(String parameter, List<String> errors) {
        NumberStringValidator validator = new NumberStringValidator();
        if (validator.validate(parameter))
            return Integer.parseInt(parameter);
        errors.add(validator.getErrorMessage());
        return 0;
    }

    private LocalDate getValidDate(String parameter, List<String> errors) {
        DateStringValidator validator = new DateStringValidator();
        LocalDate extractedDate;
        if (validator.validate(parameter))
            extractedDate = DateHelper.getLocalDateFromString(parameter);
        else {
            errors.add(validator.getErrorMessage());
            extractedDate = LocalDate.now().minusDays(1);
        }
        if (extractedDate.isBefore(LocalDate.now()))
            errors.add(INVALID_PROCESSING_DATE);
        return extractedDate;
    }
}
