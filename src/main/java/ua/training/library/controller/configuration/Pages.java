package ua.training.library.controller.configuration;

public interface Pages {

    String JSP_DIR = "/WEB-INF/views/jsp/";
    String JSP = ".jsp";

    String HOME_PAGE = JSP_DIR + "home" + JSP;
    String PROFILE_PAGE = JSP_DIR + "userProfile" + JSP;
    String LOGIN_PAGE = JSP_DIR + "login" + JSP;
    String REGISTRATION_FORM_PAGE = JSP_DIR + "registrationForm" + JSP;
    String CLIENTS_LIST_PAGE = JSP_DIR + "clientsList" + JSP;
    String CATALOG_LIST_PAGE = JSP_DIR + "catalogList" + JSP;
    String ORDER_FORM_PAGE = JSP_DIR + "orderForm" + JSP;
    String ORDERS_LIST_PAGE = JSP_DIR + "ordersList" + JSP;
    String LIBRARY_RESPONSE_FORM_PAGE = JSP_DIR + "libraryResponseForm" + JSP;
    String LIBRARY_RESPONSES_LIST_PAGE = JSP_DIR + "libraryResponsesList" + JSP;
    String LIBRARY_RESPONSE_COMPLETING_FORM = JSP_DIR + "libraryResponseCompletingForm" + JSP;

//    String ERROR_PAGE = JSP_DIR + "errorView" + JSP;
    String ERROR_403_PAGE = JSP_DIR + "/error/403" + JSP;
    String ERROR_404_PAGE = JSP_DIR + "/error/404" + JSP;
    String ERROR_500_PAGE = JSP_DIR + "/error/500" + JSP;

//    String ACCESS_DENIED_PAGE = JSP_DIR + "accessDeniedView" +JSP;

//    String REDIRECT = JSP_DIR + "_redirect" + JSP;
    String REDIRECT = "_redirect";


}
