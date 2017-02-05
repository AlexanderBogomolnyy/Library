package ua.training.library.controller.validator;


public class NameValidator extends RegexValidator {

    private static final String NAME_REGEX = "^([A-ZА-ЯЄЇ][a-zа-яєї']{1,50})$";

    public NameValidator(String message) {
        super(NAME_REGEX, message);
    }
}
