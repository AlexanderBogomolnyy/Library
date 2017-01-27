package ua.training.library.controller.configuration;

public class Paths {

    public static final String ID = "{id}";
    public static final String DIVIDER = "/";
    public static final String BASE = "/library";

    public static final String HOME = BASE + "/";
    public static final String REGISTRATION = BASE + "/registration";
    public static final String LOGIN = BASE + "/login";
    public static final String LOGOUT = BASE + "/logout";

    public static final String USERS = BASE + "/users";
    public static final String CATALOG = BASE + "/catalog";
    public static final String CLIENTS = BASE + "/clients";
    public static final String CLIENT_PROFILE = USERS + "/profile" + DIVIDER +ID;
    public static final String ORDERS = BASE + "/orders";

    public static final String LANG_EN = BASE + "/en";
    public static final String LANG_UA = BASE + "/ua";
    public static final String LANG_RU = BASE + "/ru";

    public static final String ERROR = BASE + "/error";
    public static final String ACCESS_DENIED = BASE + "/access_denied";

}
