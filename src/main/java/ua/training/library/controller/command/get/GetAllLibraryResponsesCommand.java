package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.messages.LoggingMessages;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.model.entity.LibraryResponse;
import ua.training.library.model.entity.User;
import ua.training.library.service.LibraryResponseService;
import ua.training.library.service.basic.BasicLibraryResponseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllLibraryResponsesCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GetAllLibraryResponsesCommand.class);

    private LibraryResponseService service = BasicLibraryResponseService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User loggedInUser = getUserFromSession(request);
        try {
            List<LibraryResponse> libraryResponseList = service.getAvailableForUser(loggedInUser.getId(), loggedInUser.getRole());
            logger.info(LoggingMessages.LIBRARIAN_RESPONSE_LIST_EXTRACTED);
            request.setAttribute(Attributes.LIST_OF_LIBRARIAN_RESPONSES, libraryResponseList);
            return Pages.LIBRARY_RESPONSES_LIST_PAGE;
        } catch (RuntimeException ex) {
            logger.warn(LoggingMessages.LIBRARIAN_RESPONSE_LIST_ERROR, ex);
            setErrorMessageToSession(request, ex.getMessage());
            response.sendError(500);
            return Pages.REDIRECT;
        }
    }
}
