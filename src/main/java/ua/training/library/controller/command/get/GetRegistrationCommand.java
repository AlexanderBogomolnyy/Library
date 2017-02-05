package ua.training.library.controller.command.get;

import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.REGISTRATION_FORM_PAGE;
    }
}
