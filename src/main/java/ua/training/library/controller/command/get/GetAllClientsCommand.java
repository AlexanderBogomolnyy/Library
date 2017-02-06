package ua.training.library.controller.command.get;

import ua.training.library.messages.LoggingMessages;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.model.entity.User;
import org.apache.log4j.Logger;
import ua.training.library.service.UserService;
import ua.training.library.service.basic.BasicUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllClientsCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GetAllClientsCommand.class);

    private final UserService service = BasicUserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User loggedInUser = getUserFromSession(request);
        try {
            List<User> clientList = service.getAllClients(loggedInUser.getRole());
            logger.info(LoggingMessages.CLIENT_LIST_EXTRACTED);
            request.setAttribute(Attributes.LIST_OF_CLIENTS, clientList);
            return Pages.CLIENTS_LIST_PAGE;
        } catch (RuntimeException ex) {
            logger.warn(LoggingMessages.CLIENT_LIST_ERROR + " " + ex.getMessage(), ex);
            setErrorMessageToSession(request, ex.getMessage());
            response.sendError(500);
            return Pages.REDIRECT;
        }
    }
}
