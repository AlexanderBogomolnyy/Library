package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.states.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetErrorCommand implements Command {

    private static final Logger logger = Logger.getLogger(Error.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Role role) throws IOException {
        if(!request.getRequestURI().contains(Paths.CUT_ERROR)) {
            logger.warn(LoggingMessages.CONTROLLER_ERROR_PAGE + request.getRequestURI());
//            try {
                response.sendRedirect(request.getContextPath() + Paths.ERROR);
                return  Pages.REDIRECT;
//            } catch (IOException e) {
//                logger.info(LoggingMessages.CONTROLLER_ERROR_IN_REDIRECTION);
//            }
        }
        return Pages.ERROR_PAGE;
    }
}
