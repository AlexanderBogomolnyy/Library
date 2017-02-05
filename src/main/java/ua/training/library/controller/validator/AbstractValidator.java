package ua.training.library.controller.validator;

public abstract class AbstractValidator<T> implements Validator<T> {

    private String message = "";

    protected boolean isValid = false;

    public AbstractValidator(String message) {
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return isValid ? "" : message;
    }
}
