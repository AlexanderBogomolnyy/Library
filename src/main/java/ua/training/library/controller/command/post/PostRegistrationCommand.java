package ua.training.library.controller.command.post;

import ua.training.library.config.ActionMessages;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.controller.validator.*;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.ActivationStatus;
import ua.training.library.model.entity.states.Role;
import ua.training.library.model.util.PasswordHelper;
import ua.training.library.service.UserService;
import ua.training.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class PostRegistrationCommand extends AbstractCommand {

    public static final String INVALID_FIRST_NAME_KEY = "invalid.first_name";
    public static final String INVALID_LAST_NAME_KEY = "invalid.second_name";
    public static final String INVALID_ROLE = "invalid.role";

    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> errors = new ArrayList<>();
        User user = parseFormData(request, errors);
        if (errors.isEmpty()) {
            try {
                userService.create(user);
                setSuccessMessageToSession(request, ActionMessages.SUCCESS_IN_CREATING_USER);
                response.sendRedirect(Paths.BASE + Paths.HOME);
                return Pages.REDIRECT;
            } catch (RuntimeException ex) {
                errors.add(ex.getMessage());
                errors.add("User with such login or email already present.");
            }
        }
        //TODO localize error messages
        setErrorMessageToSession(request, Arrays.toString(errors.toArray()));
        request.setAttribute(Attributes.USER_ON_REGISTRATION, user);
        return Pages.REGISTRATION_FORM_PAGE;
    }

    private User parseFormData(HttpServletRequest request, List<String> errors) {
        return new User.Builder()
                .setFirstName(validString(new NameValidator(INVALID_FIRST_NAME_KEY), request.getParameter("firstName"), errors))
                .setLastName(validString(new NameValidator(INVALID_LAST_NAME_KEY), request.getParameter("lastName"), errors))
                .setLogin(validString(new LoginValidator(), request.getParameter("login"), errors))
                .setEmail(validString(new EmailValidator(), request.getParameter("email"), errors))
                .setPassword(PasswordHelper.getSecurePassword(validString(new PasswordValidator(), request.getParameter("password"), errors)))
                .setRole(validRoleEnum(request.getParameter("role"), errors))
                .setStatus(ActivationStatus.ACTIVE)
                .build();
    }

    private String validString(Validator<String> validator, String parameter, List<String> errors) {
        if (validator.validate(parameter)) {
            return parameter;
        }
        errors.add(validator.getErrorMessage());
        return "";
    }

    private Role validRoleEnum(String parameter, List<String> errors) {
        EnumValidator validator = new EnumValidator(Role.class, INVALID_ROLE);
        if (validator.validate(parameter))
            return Role.valueOf(parameter);
        else {
            errors.add(validator.getErrorMessage());
            return Role.CLIENT;
        }
    }

}
