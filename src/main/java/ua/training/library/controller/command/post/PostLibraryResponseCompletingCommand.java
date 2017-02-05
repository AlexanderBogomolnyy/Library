package ua.training.library.controller.command.post;

import org.apache.log4j.Logger;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.LibraryResponse;
import ua.training.library.service.LibraryResponseService;
import ua.training.library.service.impl.LibraryResponseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class PostLibraryResponseCompletingCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(PostLibraryResponseCompletingCommand.class);

    private final LibraryResponseService service = LibraryResponseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getIdFromRequestURI(request);
        try {
            Optional<LibraryResponse> libraryResponse = service.getById(id);
            if (!libraryResponse.isPresent()) {
                setErrorMessageToSession(request, "This response is unavailable now.");
            } else {
                service.completeLibraryResponseByCurrentDate(id, getUserFromSession(request).getRole());
                setSuccessMessageToSession(request, "Library response completed!");
            }
        } catch (RuntimeException ex) {
            setErrorMessageToSession(request, ex.getMessage());
            response.sendError(500);
            return Pages.REDIRECT;
        }
        response.sendRedirect(getBaseUrlByRole(request) + Paths.LIBRARY_RESPONSES);
        return Pages.REDIRECT;
    }
}
