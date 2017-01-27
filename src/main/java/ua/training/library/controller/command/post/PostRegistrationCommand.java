package ua.training.library.controller.command.post;

import ua.training.library.config.ActionMessages;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.ActivationStatus;
import ua.training.library.model.entity.states.Role;
import ua.training.library.model.util.PasswordHelper;
import ua.training.library.service.UserService;
import ua.training.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostRegistrationCommand implements Command {


    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
//        session.setAttribute(Attributes.ERROR_MESSAGE, "Success");

        User user = new User.Builder()
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setLogin(request.getParameter("login"))
                .setEmail(request.getParameter("email"))
                .setPassword(PasswordHelper.getSecurePassword(request.getParameter("password")))
                .setRole(Role.valueOf(request.getParameter("role")))
                .setStatus(ActivationStatus.ACTIVE)
                .build();

        try {
            userService.create(user);
            request.setAttribute(Attributes.SUCCESS_MESSAGE, ActionMessages.SUCCESS_IN_CREATING_USER);
            response.sendRedirect(Paths.HOME);
            return Pages.REDIRECT;
        } catch (Exception ex) {
            request.setAttribute(Attributes.ERROR_MESSAGE, ex.getMessage());
            request.setAttribute(Attributes.USER_ON_REGISTRATION, user);
            return Pages.REGISTRATION_PAGE;
        }
    }
}
