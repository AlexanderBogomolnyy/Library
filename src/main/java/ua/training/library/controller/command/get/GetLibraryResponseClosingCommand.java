package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.LibraryResponse;
import ua.training.library.service.LibraryResponseService;
import ua.training.library.service.impl.LibraryResponseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class GetLibraryResponseClosingCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GetLibraryResponseClosingCommand.class);

    private final LibraryResponseService service = LibraryResponseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getIdFromRequestURI(request);
        try {
            Optional<LibraryResponse> libraryResponse = service.getById(id);
            LibraryResponse completedLibraryResponse = libraryResponse.orElseGet(LibraryResponse::new);
            completedLibraryResponse.setDateOfReturn(LocalDate.now());
            request.setAttribute(Attributes.LIBRARY_RESPONSE, completedLibraryResponse);

            request.setAttribute(Attributes.RESPONSE_PATH, request.getRequestURI());

            if (!libraryResponse.isPresent()) {
                setErrorMessageToSession(request, "This response is unavailable now.");
                // TODO correct redirect considering user Role
                response.sendRedirect(getBaseUrlByRole(request) + Paths.LIBRARY_RESPONSES);
                return Pages.REDIRECT;
            }
        } catch (RuntimeException ex) {
            setErrorMessageToSession(request, ex.getMessage());
            response.sendError(500);
            return Pages.REDIRECT;
        }
        return Pages.LIBRARY_RESPONSE_COMPLETING_FORM;
    }
}
