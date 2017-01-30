package ua.training.library.controller.command.get;

import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.model.entity.states.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Role role) {
        return Pages.REGISTRATION_PAGE;
    }
}
