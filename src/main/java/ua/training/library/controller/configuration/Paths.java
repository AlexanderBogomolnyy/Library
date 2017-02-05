package ua.training.library.controller.configuration;

public class Paths {

    public static final String ID = "{id}";
    public static final String DELIMITER = "/";
    public static final String BASE = "/library";
    public static final String PROFILE = "/profile";

    public static final String HOME = "/";

    public static final String REGISTRATION = "/registration";
    public static final String LOGIN = "/login";

    public static final String LOGOUT = "/logout";

    public static final String COMPLETING = "/completing";

    public static final String CLIENTS = "/clients";

    public static final String CATALOG = "/catalog";

    public static final String ORDERS = "/orders";
    public static final String ORDER = "/order";
    public static final String ORDER_FORM = CATALOG + DELIMITER + ID + ORDER;
    public static final String ORDER_COMPLETING = ORDERS + DELIMITER + ID + COMPLETING;

    public static final String LIBRARY_RESPONSES = "/responses";
    public static final String LIBRARY_RESPONSE = "/response";
    public static final String LIBRARY_RESPONSE_FORM = ORDERS + DELIMITER + ID + LIBRARY_RESPONSE;
    public static final String LIBRARY_RESPONSE_CLOSING = LIBRARY_RESPONSES + DELIMITER + ID + COMPLETING;

    public static final String LANG_EN = "/en";
    public static final String LANG_UA = "/ua";
    public static final String LANG_RU = "/ru";

    public static final String ERROR = "/error";
    public static final String ERROR_403 = "/403";
    public static final String ERROR_404 = "/404";
    public static final String ERROR_500 = "/500";

}
