package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetLogoutCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetLogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attributes.LOGINED_USER);
        if (user != null) {
            session.setAttribute(Attributes.LOGINED_USER, null);
            session.invalidate();
        }
        response.sendRedirect(Paths.BASE + Paths.LOGIN);
        return Pages.REDIRECT;
    }
}
