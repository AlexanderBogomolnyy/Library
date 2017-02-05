package ua.training.library.controller.validator;


import java.util.regex.Pattern;

public class RegexValidator extends AbstractValidator<String> {

    private Pattern pattern;

    public RegexValidator(String regex, String message) {
        super(message);
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean validate(String string) {
        isValid = string != null && pattern.matcher(string).matches();
        return isValid;
    }
}
