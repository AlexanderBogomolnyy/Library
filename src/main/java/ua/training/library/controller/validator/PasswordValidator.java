package ua.training.library.controller.validator;


public class PasswordValidator extends RegexValidator {

    private final static String INVALID_PASSWORD_KEY = "invalid.password";

    private static final String PASSWORD_REGEX = "^[a-z0-9_-]{4,20}";

    public PasswordValidator() {
        super(PASSWORD_REGEX, INVALID_PASSWORD_KEY);
    }
}
