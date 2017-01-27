package ua.training.library.controller;

import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.Command;
import org.apache.log4j.Logger;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "mainController", urlPatterns = {"/library/*"})
public class MainController extends HttpServlet {

    private static final long serialVersionUID = -8047911912839526913L;

    private static final Logger logger = Logger.getLogger(MainController.class);

    private static RoleManager manager = RoleManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info(LoggingMessages.CONTROLLER_GET_REQUEST + request.getRequestURI());
        processCommand(executeCommand(request), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info(LoggingMessages.CONTROLLER_POST_REQUEST + request.getRequestURI());
        processCommand(executeCommand(request), request, response);
    }

    private void processCommand(Command command, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String page = command.execute(request, response);
        if (!page.equals(Pages.REDIRECT)) {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    private Command executeCommand(HttpServletRequest request) {
        String commandRequest = getCommandRequest(request);
        Role userRole = getUserRole(request);
        return manager.getCommand(commandRequest, userRole);
    }

    private String getCommandRequest(HttpServletRequest request) {
        String requestWithoutNumbers = request.getRequestURI().replaceAll("\\d+", Paths.ID);
        return request.getMethod().toUpperCase() + ":" + requestWithoutNumbers;
    }

    private Role getUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User userInSession = (User) session.getAttribute(Attributes.LOGINED_USER);
        boolean checkUser = userInSession != null && userInSession.getRole() != null;
        return checkUser ? userInSession.getRole() : Role.ANONYMOUS;
    }

}