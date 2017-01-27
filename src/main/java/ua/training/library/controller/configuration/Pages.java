package ua.training.library.controller.configuration;

public interface Pages {

    String JSP_DIR = "/WEB-INF/views/jsp/";
    String JSP = ".jsp";

    String HOME_PAGE = JSP_DIR + "homeView" + JSP;
    String PROFILE_PAGE = JSP_DIR + "profileView" + JSP;
    String LOGIN_PAGE = JSP_DIR + "loginView" + JSP;
    String REGISTRATION_PAGE = JSP_DIR + "registrationView" + JSP;
    String CLIENTS_PAGE = JSP_DIR + "clientsView" + JSP;

    String ERROR_PAGE = JSP_DIR + "errorView" + JSP;
    String ACCESS_DENIED_PAGE = JSP_DIR + "accessDeniedView" +JSP;

    String REDIRECT = JSP_DIR + "_redirect" + JSP;
}
