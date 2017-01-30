package ua.training.library.controller.command.get;

import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;
import org.apache.log4j.Logger;
import ua.training.library.service.UserService;
import ua.training.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllClientsCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetAllClientsCommand.class);

    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Role role) throws IOException {
        try {
            List<User> clientList = service.getAllAvailable(role);
            logger.info(LoggingMessages.CLIENT_LIST_EXTRACTED);
            request.setAttribute(Attributes.LIST_OF_CLIENTS, clientList);
            return Pages.CLIENTS_PAGE;
        } catch (RuntimeException ex) {
            logger.warn(LoggingMessages.CLIENT_LIST_ERROR + " " + ex.getMessage(), ex);
            response.sendRedirect(Paths.ERROR);
            return Pages.REDIRECT;
        }
    }
}
