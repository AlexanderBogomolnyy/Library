package ua.training.library.controller.validator;


public class EmailValidator extends RegexValidator {

    private final static String INVALID_EMAIL_KEY = "invalid.email";

    private static final String EMAIL_REGEX = "^(.+\\@.+\\..+)$";

    public EmailValidator() {
        super(EMAIL_REGEX, INVALID_EMAIL_KEY);
    }
}
