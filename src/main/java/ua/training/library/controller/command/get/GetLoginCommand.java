package ua.training.library.controller.command.get;

import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetLoginCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attributes.LOGINED_USER);
        if (user != null) {
            String userProfileURI = getBaseUrlByRole(request) + Paths.PROFILE;
            response.sendRedirect(userProfileURI);
            return Pages.REDIRECT;
        }
        return Pages.LOGIN_PAGE;
    }
}
