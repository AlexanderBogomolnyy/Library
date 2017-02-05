package ua.training.library.controller.validator;


public class NumberStringValidator extends RegexValidator {

    private final static String INVALID_NUMBER_KEY = "invalid.number";

    private static final String NUMBER_REGEX = "\\d+";

    public NumberStringValidator() {
        super(NUMBER_REGEX, INVALID_NUMBER_KEY);
    }
}
