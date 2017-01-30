package ua.training.library.controller.command.get;

import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;
import org.apache.log4j.Logger;
import ua.training.library.model.entity.states.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetLoginCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetLoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Role role) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attributes.LOGINED_USER);
        if (user != null) {
            String userProfileURI = convertURI(user);
//            request.setAttribute(Attributes.REDIRECT, userProfileURI);
            logger.info("redirect: " + userProfileURI);
            try {
                response.sendRedirect(userProfileURI);
                return Pages.REDIRECT;
            } catch (IOException e) {
                logger.error(LoggingMessages.CONTROLLER_ERROR_IN_REDIRECTION);
                return Pages.ERROR_PAGE;
            }
        }
        return Pages.LOGIN_PAGE;
    }

    private String convertURI(User user) {
//        return Paths.CLIENT_PROFILE.replace(Paths.ID, String.valueOf(user.getId()));
        return Paths.BASE + Paths.DELIMITER + user.getRole().name().toLowerCase() + Paths.PROFILE;
    }
}
