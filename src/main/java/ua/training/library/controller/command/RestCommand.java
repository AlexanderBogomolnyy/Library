package ua.training.library.controller.command;

import javax.servlet.http.HttpServletRequest;

public abstract class RestCommand implements Command {

    public int getIdFromRequestURI(HttpServletRequest request) {
        String stringId = request.getRequestURI().replaceAll("\\D+", "");
        return Integer.parseInt(stringId);
    }

}
