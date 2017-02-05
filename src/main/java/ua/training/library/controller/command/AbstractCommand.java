package ua.training.library.controller.command;

import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractCommand implements Command {

    public int getIdFromRequestURI(HttpServletRequest request) {
        String stringId = request.getRequestURI().replaceAll("\\D+", "");
        return Integer.parseInt(stringId);
    }

    public String getBaseUrlByRole(HttpServletRequest request) {
        User loggedInUser = getUserFromSession(request);
        return Paths.BASE + Paths.DELIMITER + loggedInUser.getRole().name().toLowerCase();
    }

    public User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute(Attributes.LOGINED_USER);
    }

    public void setErrorMessageToSession(HttpServletRequest request, String message) {
        setMessageToSession(request, Attributes.ERROR_MESSAGE, message);
    }

    public void setSuccessMessageToSession(HttpServletRequest request, String message) {
        setMessageToSession(request, Attributes.SUCCESS_MESSAGE, message);
    }

    private void setMessageToSession(HttpServletRequest request, String messageType, String message) {
        HttpSession session = request.getSession();
        session.setAttribute(messageType, message);
    }

    public boolean validateFormField(String parameterValue, String pattern) {
        return parameterValue.matches(pattern);
    }

}
