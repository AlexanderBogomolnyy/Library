package ua.training.library.controller.command;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedCommand implements Command {

    private static final Logger logger = Logger.getLogger(AccessDeniedCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(!request.getRequestURI().contains(Paths.ACCESS_DENIED)) {
            logger.warn(LoggingMessages.CONTROLLER_ACCESS_DENIED + request.getRequestURI());
            try {
                response.sendRedirect(request.getContextPath() + Paths.ACCESS_DENIED);
                return  Pages.REDIRECT;
            } catch (IOException e) {
                logger.info(LoggingMessages.CONTROLLER_ERROR_IN_REDIRECTION);
            }
        }
        return Pages.ACCESS_DENIED_PAGE;
    }
}
