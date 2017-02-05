package ua.training.library.controller.command;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.configuration.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorCommand implements Command {

    private static final String LINK_403 = "/403";
    private static final String LINK_404 = "/404";
    private static final String LINK_500 = "/500";

    private static final Logger logger = Logger.getLogger(Error.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();
        logger.warn(LoggingMessages.CONTROLLER_ERROR_PAGE + requestURI);
        if (requestURI.endsWith(LINK_403))
            return Pages.ERROR_403_PAGE;
        if (requestURI.endsWith(LINK_404))
            return Pages.ERROR_404_PAGE;
        if (requestURI.endsWith(LINK_500))
            return Pages.ERROR_500_PAGE;
        return Pages.ERROR_404_PAGE;
    }
}
