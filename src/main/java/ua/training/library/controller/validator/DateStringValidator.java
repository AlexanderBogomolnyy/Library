package ua.training.library.controller.validator;

import ua.training.library.model.util.DateHelper;

import java.time.format.DateTimeParseException;

public class DateStringValidator extends RegexValidator {

    private final static String INVALID_DATE_KEY = "invalid.date";

    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public DateStringValidator() {
        super(DATE_REGEX, INVALID_DATE_KEY);
    }

    @Override
    public boolean validate(String string) {
        if (!super.validate(string))
            return false;
        try {
            DateHelper.getLocalDateFromString(string);
            isValid = true;
        } catch (DateTimeParseException ex) {
            isValid = false;
        }
        return isValid;
    }
}
