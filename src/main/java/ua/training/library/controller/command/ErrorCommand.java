package ua.training.library.controller.command;

import ua.training.library.controller.configuration.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.ERROR_PAGE;
    }
}
