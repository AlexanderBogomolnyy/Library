package ua.training.library.controller.command.get;

import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.controller.i18n.Languages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class ChangeLanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String[] uriComponents = request.getRequestURI().split(Paths.DELIMITER);
        String ending = uriComponents[uriComponents.length - 1];
        Locale locale = new Locale(ending, ending.toUpperCase());
        if (Languages.isSupported(locale)) {
            session.setAttribute(Attributes.LOCALE, locale);
        }
        String redirect = getRedirectQuery(request);
        response.sendRedirect(redirect);
        return Pages.REDIRECT;
    }

    private String getRedirectQuery(HttpServletRequest request) {
        String referer = request.getHeader(Attributes.PAGE_REFERER);
        if (referer == null || referer.isEmpty())
            return request.getServletPath();
        return referer;
    }
}
