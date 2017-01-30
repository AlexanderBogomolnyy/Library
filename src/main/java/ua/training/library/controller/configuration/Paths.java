package ua.training.library.controller.configuration;

public class Paths {

    public static final String ID = "{id}";
    public static final String DELIMITER = "/";
    public static final String BASE = "/library";
    public static final String CLIENT_SECTION = "/client";
    public static final String LIBRARIAN_SECTION = "/librarian";
    public static final String PROFILE = "/profile";

//    public static final String HOME = BASE + "/";
    public static final String HOME = "/";

    public static final String REGISTRATION = "/registration";
    public static final String LOGIN = "/login";



    public static final String LOGOUT = "/logout";

//    public static final String USERS = BASE + "/users";
    public static final String CATALOG = "/catalog";
    public static final String CLIENTS = "/clients";
//    public static final String CLIENT_PROFILE = USERS + "/profile" + DELIMITER +ID;
    public static final String ORDERS = "/orders";
    public static final String ORDER = "/order";
    public static final String ORDER_FORM = CATALOG + DELIMITER + ID + ORDER;

    public static final String LIBRARY_RESPONSES = "/responses";
    public static final String LIBRARY_RESPONSE = "/response";
    public static final String LIBRARY_RESPONSE_FORM = ORDERS + DELIMITER + ID + LIBRARY_RESPONSE;

    public static final String LANG_EN = "/en";
    public static final String LANG_UA = "/ua";
    public static final String LANG_RU = "/ru";

    public static final String ERROR = BASE + "/error";
    public static final String ACCESS_DENIED = BASE + "/access_denied";

    public static final String CUT_ERROR = "/error";
    public static final String CUT_ACCESS_DENIED = "/access_denied";


}
