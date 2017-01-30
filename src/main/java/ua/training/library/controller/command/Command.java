package ua.training.library.controller.command;

import ua.training.library.model.entity.states.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response, Role role) throws IOException;

}
