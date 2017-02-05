package ua.training.library.controller.command.post;

import ua.training.library.config.ActionMessages;
import ua.training.library.controller.command.AbstractCommand;
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

public class PostLoginCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(PostLoginCommand.class);

    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        User uncheckedUser = getUncheckedUser(login);
        try {
            Optional<User> user = service.login(login, PasswordHelper.getSecurePassword(password));
            User userInSession = user.orElse(uncheckedUser);
            session.setAttribute(Attributes.LOGINED_USER, userInSession);
            if (user.isPresent()) {
                String userProfileURI = getBaseUrlByRole(request) + Paths.PROFILE;
                setSuccessMessageToSession(request, ActionMessages.SUCCESS_IN_LOGIN);
                response.sendRedirect(userProfileURI);
                return Pages.REDIRECT;
            }
        } catch (RuntimeException ex) {
            setErrorMessageToSession(request, ex.getMessage());
            response.sendError(500);
            return Pages.REDIRECT;
        }
        setErrorMessageToSession(request, ActionMessages.ERROR_IN_LOGIN);
        logger.warn("Error in authentication.");
        return Pages.LOGIN_PAGE;
    }

    private User getUncheckedUser(String login) {
        return new User.Builder()
                .setId(0)
                .setLogin(login)
                .setRole(Role.ANONYMOUS)
                .build();
    }
}
