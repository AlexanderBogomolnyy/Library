package ua.training.library.controller.command.get;

import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.PROFILE_PAGE;
    }

}
