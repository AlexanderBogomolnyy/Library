package ua.training.library.controller.command.get;

import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;
import org.apache.log4j.Logger;
import ua.training.library.service.UserService;
import ua.training.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetLoginCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetLoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attributes.LOGINED_USER);
        if (user != null) {
            String userProfileURI = Paths.CLIENT_PROFILE.replace(Paths.ID, String.valueOf(user.getId()));
            request.setAttribute(Attributes.REDIRECT, userProfileURI);
            logger.info("redirect: " + userProfileURI);
            return Pages.REDIRECT;
        }
        return Pages.LOGIN_PAGE;
    }
}
