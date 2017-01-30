package ua.training.library.controller.command.post;


import ua.training.library.config.ActionMessages;
import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Parameters;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;
import ua.training.library.model.util.PasswordHelper;
import org.apache.log4j.Logger;
import ua.training.library.service.UserService;
import ua.training.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class PostLoginCommand implements Command {

    private static final Logger logger = Logger.getLogger(PostLoginCommand.class);

    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Role role) {
        HttpSession session = request.getSession();
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        if (login != null && password != null) {
            Optional<User> user = service.login(login, PasswordHelper.getSecurePassword(password));
            if(user.isPresent()) {
                session.setAttribute(Attributes.LOGINED_USER, user.get());
//                String userProfileURI = Paths.CLIENT_PROFILE.replace(Paths.ID, String.valueOf(user.get().getId()));
                String userProfileURI = Paths.BASE + Paths.DELIMITER + user.get().getRole().name().toLowerCase() + Paths.PROFILE;
                session.setAttribute(Attributes.PROFILE_LINK, userProfileURI);
//                request.setAttribute(Attributes.REDIRECT, userProfileURI);
                request.setAttribute(Attributes.SUCCESS_MESSAGE, ActionMessages.SUCCESS_IN_LOGIN);
                try {
                    response.sendRedirect(userProfileURI);
                    return Pages.REDIRECT;
                } catch (IOException e) {
                    logger.error(LoggingMessages.CONTROLLER_ERROR_IN_REDIRECTION);
                }
            }
        }
        User user = new User.Builder()
                .setId(0)
                .setLogin(login)
                .setRole(Role.ANONYMOUS)
                .build();
        session.setAttribute(Attributes.LOGINED_USER, user);
        request.setAttribute(Attributes.ERROR_MESSAGE, ActionMessages.ERROR_IN_LOGIN);
        return Pages.LOGIN_PAGE;
    }
}
