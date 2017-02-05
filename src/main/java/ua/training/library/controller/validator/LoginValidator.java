package ua.training.library.controller.validator;


public class LoginValidator extends RegexValidator {

    private final static String INVALID_LOGIN_KEY = "invalid.login";

    private static final String LOGIN_REGEX = "^[a-z0-9_-]{3,25}$";

    public LoginValidator() {
        super(LOGIN_REGEX, INVALID_LOGIN_KEY);
    }
}
